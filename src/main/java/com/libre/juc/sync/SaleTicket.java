package com.libre.juc.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ZC
 * @date 2021/7/10 22:05
 */
public class SaleTicket {
    public static final Integer MAX_TICKET = 40000;
    public static void main(String[] args) {

        Ticket ticket = new Ticket(30000);

        new Thread(() -> {
            for (int i = 0; i < MAX_TICKET; i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < MAX_TICKET; i++) {
                ticket.sale();
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < MAX_TICKET; i++) {
                ticket.sale();
            }
        },"C").start();
    }
}

@Slf4j
class Ticket {

    private int ticketNum;

    Ticket(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public void sale() {
       synchronized (this) {
           if (ticketNum > 0) {
               log.info("{}卖出了: {} 张票,还剩: {} 张票",Thread.currentThread().getName(), ticketNum--, ticketNum);
           }
       }
    }

}
