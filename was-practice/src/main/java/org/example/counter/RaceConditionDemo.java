package org.example.counter;

public class RaceConditionDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread thread1 = new Thread(counter, "Thread-1");
        Thread thread2 = new Thread(counter, "Thread-2");
        Thread thread3 = new Thread(counter, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        // 싱글톤 객체에서 상태를 유지하게 설계하면 문제가 발생한다.
        /** 출력
         * Value for Thread After IncrementThread2 2
         * Value for Thread After IncrementThread3 3
         * Value for Thread After IncrementThread1 2
         * Value for Thread at lastThread1 0
         * Value for Thread at lastThread2 2
         * Value for Thread at lastThread3 1
         */
    }
}
