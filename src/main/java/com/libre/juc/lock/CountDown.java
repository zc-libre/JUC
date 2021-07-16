package com.libre.juc.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author ZC
 * @date 2021/7/12 1:21
 */
public class CountDown {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    //准备完毕……运动员都阻塞在这，等待号令
                    System.out.println(Thread.currentThread().getName() + " 准备完毕……运动员都阻塞在这，等待号令");
                    countDownLatch.await();
                    String parter = "【" + Thread.currentThread().getName() + "】";
                    System.out.println(parter + "开始执行……");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("裁判准备发令");
        Thread.sleep(2000);// 裁判准备发令
        System.out.println("发令枪：执行发令");
        countDownLatch.countDown();// 发令枪：执行发令
    }
}
