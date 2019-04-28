package com.atguigu.juc;

/**
 * Copyright (C), 2015-2019, 硅谷精英股份有限公司
 *
 * @author Lee
 * 2019/4/22 11:47
 */
public class  SoldCards{
    public static void main(String[] args) {
        Ticket ticket=new Ticket(30);
        Thread T1 = new Thread(ticket ,"a");
        Thread T2 = new Thread(ticket ,"b");
        Thread T3 = new Thread(ticket ,"c");
        T1.start();
        T2.start();
        T3.start();
    }
        }

class Ticket implements Runnable {
    private int num;
    public Ticket(int num) {
        this.num = num;
    }
    @Override
    public void run() {
        while (true) {

            synchronized ("") {
                try {
                    if (num < 1) {
                        System.out.println("车票已售罄!");
                        return;
                    }
                    // 获取当前线程名字
                    String threadName = Thread.currentThread().getName();
                    // 打印火车票,休眠20毫秒模拟打印车票时间
                    Thread.sleep(20);
                    System.out.println(threadName + " 售出第" + num-- + "张火车票，剩余No." + num);
                    // 某线程售完最后一张车票时,放出"车票已售罄"提示
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

