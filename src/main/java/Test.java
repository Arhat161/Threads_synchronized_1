public class Test {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.doWork();
    }

    // Use the word 'synchronized' to ensure that each thread accesses the 'counter' variable in turn
    public synchronized void increment() {
        counter++;
    }

    // use anonymous class for implements interface Runnable when we create new threads
    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join(); // in current thread 'Main' we wait when thread 'thread1' finish his work
        thread2.join(); // in current thread 'Main' we wait when thread 'thread2' finish his work

        System.out.println(counter);
    }
}
