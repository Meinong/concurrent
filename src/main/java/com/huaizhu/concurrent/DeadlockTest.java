package com.huaizhu.concurrent;

public class DeadlockTest {

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        new Thread(()->{
            synchronized (a){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (b){
                    System.out.println("------");
                }
            }
        }).start();

        Thread.sleep(2000);
        new Thread(()->{
            synchronized (b){
                synchronized (a){
                    System.out.println("aaaaa");
                }
            }
        }).start();
    }
}
