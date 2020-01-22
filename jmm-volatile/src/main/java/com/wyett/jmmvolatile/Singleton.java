package com.wyett.jmmvolatile;

/**
 * @author : wyettLei
 * @date : Created in 2020/1/21 17:33
 * @description: TODO
 */

public class Singleton {
    /**
     * 查看汇编指令
     * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
     */
    private volatile static Singleton myInstance;

    public static Singleton getInstance() {
        if (myInstance == null) {
            synchronized (Singleton.class) {
                if (myInstance == null) {
                    myInstance = new Singleton();
                }
            }
        }
        return myInstance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
