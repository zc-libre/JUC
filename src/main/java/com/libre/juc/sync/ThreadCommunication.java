package com.libre.juc.sync;

/**
 * 线程间通信  (虚假唤醒问题)
 * @author ZC
 * @date 2021/7/11 16:59
 */
class Share {

    private int number = 0;

    public void incr() throws InterruptedException {
        synchronized (this) {
            // 虚假唤醒问题，不能使用if
           /* if (number != 0) {
                this.wait(); // 在哪里睡，在哪里醒
            }*/
            while (number != 0) {
                this.wait();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+" :: " + number);
            this.notifyAll();
        }
    }

    public synchronized void decr() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+" :: " + number);
        this.notifyAll();
    }
}

public class ThreadCommunication {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
