package com.wyett.jmmvolatile;

/**
 * @author : wyettLei
 * @date : Created in 2020/1/22 16:45
 * @description: TODO
 */

public class VolatileReorderDemo {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for(;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;

            Thread t1 = new Thread(() -> {
                waitShort(10000);
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            String result = "第" + i + "次获取到:(x = " + x + ", y = " + y +")";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }


        }
    }

    public static void waitShort(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while(start + interval > end);
    }
}
