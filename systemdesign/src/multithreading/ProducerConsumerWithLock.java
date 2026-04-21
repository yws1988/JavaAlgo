import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLock {
    private LinkedList<Integer> queue=new LinkedList<>();
    private final int limit=10;
    private final ReentrantLock lock=new ReentrantLock();
    private final Condition bufferNotFull=lock.newCondition();
    private final Condition bufferNotEmpty=lock.newCondition();
    public synchronized void produce(Integer input) throws InterruptedException {
        lock.lock();
        try {
            while(queue.size()==limit){
                System.out.println("The buffer is complete, waiting");
                bufferNotFull.await();
            }

            queue.add(input);
            System.out.println("Producing"+input);
            bufferNotEmpty.notify();
        }finally {
            lock.unlock();
        }
    }

    public synchronized int consume() throws InterruptedException {
        lock.lock();
        try{
            while (queue.isEmpty()){
                System.out.println("Buffer empty. Consumer is waiting...");
                bufferNotEmpty.await();
            }

            int consumedValue=queue.pop();
            System.out.println("Consuming"+consumedValue);
            bufferNotFull.notify();

            return consumedValue;
        }finally {
            lock.unlock();
        }
    }

    public static void mainF(String[] args){
        ProducerConsumerWithLock buffer=new ProducerConsumerWithLock();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) buffer.produce(i);
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) buffer.consume();
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        producer.start();
        consumer.start();

    }
}


