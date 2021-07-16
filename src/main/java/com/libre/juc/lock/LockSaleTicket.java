package com.libre.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZC
 * @date 2021/7/10 23:34
 */
@Slf4j
class LockTicket {

    private int number = 30;
    private final Lock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                log.info("{}卖出了: {} 张票,还剩: {} 张票",Thread.currentThread().getName(), number--, number);
            }
        } finally {
            lock.unlock();
        }

    }
}

public class LockSaleTicket {

    public static void main(String[] args) {

        LockTicket lockTicket = new LockTicket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lockTicket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lockTicket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lockTicket.sale();
            }
        }, "C").start();
    }
}
