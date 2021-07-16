package com.libre.juc;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author ZC
 * @date 2021/7/15 18:45
 */
public class Samp {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "号车抢到了车位");

                    TimeUnit.SECONDS.sleep(new Random().nextInt(10)  );
                    System.out.println(Thread.currentThread().getName() + "号车离开了车位 " + LocalDateTime.now());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
