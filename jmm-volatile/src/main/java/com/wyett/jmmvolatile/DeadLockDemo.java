package com.wyett.jmmvolatile;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * @author : wyettLei
 * @date : Created in 2020/1/20 18:49
 * @description: TODO
 */

public class DeadLockDemo {
    private static final String  synchronize_a = "a";
    private static final String  synchronize_b = "b";

    public static void deadLock() {
        Thread threadA = new Thread(() -> {
                    synchronized (synchronize_a) {
                        System.out.println("get synchronize_a");
                        try {
                            synchronized (synchronize_b) {
                                System.out.println("try to get synchronize_b");
                            }
                        } catch (Exception e) {
                            System.out.println("waiting for a");
                        }
                    }
                }
        );

        Thread threadB = new Thread(() -> {
            synchronized (synchronize_b) {
                System.out.println("get synchronize_b");
                synchronized (synchronize_a) {
                    System.out.println("get synchronize_a");
                }
            }
        });

        threadA.start();
        threadB.start();
    }

    public static void main(String[] args) {
        deadLock();
    }
}
