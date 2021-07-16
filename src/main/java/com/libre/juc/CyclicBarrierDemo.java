package com.libre.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ZC
 * @date 2021/7/15 13:45
 */
public class CyclicBarrierDemo {
    private static final int NUMBER = 7;
    public static void main(String[] args)  {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () ->{
            System.out.println("集齐7颗龙珠可以召唤神龙！");
        });

        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "星龙珠被收集到了！");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "星龙珠第二次被收集到了！");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
