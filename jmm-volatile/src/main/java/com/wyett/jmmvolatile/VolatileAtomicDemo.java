package com.wyett.jmmvolatile;

/**
 * @author : wyettLei
 * @date : Created in 2020/1/22 10:27
 * @description: TODO
 */

public class VolatileAtomicDemo {

    private static volatile int counter = 0;
//    private static int counter = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for(int j = 0; j < 1000; j++) {
                    counter++;
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);
    }
}
