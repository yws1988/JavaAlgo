import java.util.LinkedList;

public class ProducerConsumer {
    private LinkedList<Integer> queue=new LinkedList<>();
    private final int limit=10;
    public synchronized void produce(Integer input) throws InterruptedException {
        while(queue.size()==limit){
            System.out.println("The buffer is complete, waiting");
            wait();
        }

        queue.add(input);
        System.out.println("Producing"+input);
        notifyAll();

    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()){
            System.out.println("Buffer empty. Consumer is waiting...");
            wait();
        }

        int consumedValue=queue.pop();
        System.out.println("Consuming"+consumedValue);
        notifyAll();

        return consumedValue;
    }

    public static void mainF(String[] args){
        ProducerConsumer buffer=new ProducerConsumer();

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


