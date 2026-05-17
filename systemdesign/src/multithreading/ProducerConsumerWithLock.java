package multithreading;
/***
 * ArrayBlockingQueue. It handles all the waiting and signaling logic internally using ReentrantLock and Conditions.
 * Using ReentrantLock and Condition is often considered the "cleaner" way to solve concurrency problems in modern Java.
 * Compared to the basic synchronized approach, this method gives you more control—specifically,
 * it allows you to have multiple wait-sets for the same object.
 * Why use ReentrantLock?
 * In the previous solution, notifyAll() wakes up everyone. With Condition, we can wake up producers and consumers
 * independently. This is more efficient because a producer only needs to wake up a consumer, not other producers.
 */

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLock {
    private LinkedList<Integer> queue=new LinkedList<>();
    private final int limit=10;
    private final ReentrantLock lock=new ReentrantLock();
    private final Condition bufferNotFull=lock.newCondition();
    private final Condition bufferNotEmpty=lock.newCondition();
    public void produce(Integer input) throws InterruptedException {
        lock.lock();
        try {
            while(queue.size()==limit){
                System.out.println("The buffer is complete, waiting");
                bufferNotFull.await();
            }

            queue.add(input);
            System.out.println("Producing"+input);
            bufferNotEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try{
            while (queue.isEmpty()){
                System.out.println("Buffer empty. Consumer is waiting...");
                bufferNotEmpty.await();
            }

            int consumedValue=queue.pop();
            System.out.println("Consuming"+consumedValue);
            bufferNotFull.signal();

            return consumedValue;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
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


