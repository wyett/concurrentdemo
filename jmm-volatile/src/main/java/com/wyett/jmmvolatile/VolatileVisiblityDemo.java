package com.wyett.jmmvolatile;


/**
 * @author : wyettLei
 * @date : Created in 2020/1/21 14:22
 * @description: TODO
 */

public class VolatileVisiblityDemo {

    private boolean flag = false;
    static Object object = new Object();

    public void reflash() {
        this.flag = true;
        String threadName = Thread.currentThread().getName();
        System.out.println("线程" + threadName + "修改了flag的状态");
    }

    public void load() {
        String threadName = Thread.currentThread().getName();
        int i = 0;
        while (!flag) {
            synchronized (object) {
                i++;
            }
        }
        System.out.println("线程" + threadName + "捕捉到flag发生改变" + i);
    }

    public static void main(String[] args) {
        VolatileVisiblityDemo volatileVisiblityDemo = new VolatileVisiblityDemo();
        Thread t1 = new Thread(() -> {
            volatileVisiblityDemo.reflash();
        }, "t1");

        Thread t2 = new Thread(() -> {
            volatileVisiblityDemo.load();
        }, "t2");

        t2.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }


}
