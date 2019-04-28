package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2015-2019, 硅谷精英股份有限公司
 *
 * @author Lee
 * 2019/4/22 20:34
 */

/**
 * 高内聚低耦合的前提下       线程   操作   资源类
 *
 * 拷贝小括号，写死右箭头，落地大括号（前提是函数式接口，即只有一个方法的接口）
 */
public class NewSellTicket {
    public static void main(String[] args) {
        NewTicket newTicket = new NewTicket();
        new Thread(() -> {
            for (int i = 1; i <= 30; i++) {
                newTicket.sell();
            }
        }, "a").start();


        new Thread(() -> {
            for (int i = 1; i <= 30; i++) {
                newTicket.sell();
            }

        }, "b").start();


        new Thread(() -> {
            for (int i = 1; i <= 30; i++) {
                newTicket.sell();
            }

        }, "c").start();

    }

}

class NewTicket {
    private int number = 30;
    private int i = 0;
    /**
     * 可重入锁
     */
    private Lock lock = new ReentrantLock();

    public void sell() {
        lock.lock();
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + ":卖出第" + ++i + "张票," + "还剩:" + --number + "张票");
        }
        lock.unlock();
    }

}
