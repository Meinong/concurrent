package com.huaizhu.concurrent;

import java.util.Objects;

/**
 * wait 与 sleep 的区别：
 * 1.wait 是Object 类中的方法 sleep是Thread 的方法
 * 2.调用wait方法时会释放对象的锁 sleep 方法调用时不会释放对象的锁。
 *
 * 关于 wait 与 notify 和 notifyAll 方法总结:
 * 1.当调用wait时，首先需要确保调用了wait方法的线程已经持有了对象的锁
 * 2.当调用wait后，该线程就会释放掉这个对象的锁，然后进入到等待状态（wait set）
 * 3.当线程调用了wait后进入到等待状态时，它就可以等待其他线程调用相同对象的notify 或notifyAll方法来唤醒自己
 * 4.一旦这个线程被其他线程唤醒后，该线程就会与其他线程一同开始竞争这个对象的锁（公平竞争），只有当线程获取这个对象的锁后，线程才会继续往下执行
 * 5.调用wait方法的代码片段需要放在一个synchroinzed块或是synchronized 方法中，这样才可以确保线程在调用wait方法前已经获取到了对象的锁
 * 6.当调用对象的notify 方法时，它会随机唤醒该对象等待集合（wait set）中的任意一个线程，当某个线程被唤醒后，它就会与其他线程一同竞争对象的锁
 * 7.当调用对象的notifyALlf方法时，它就会唤醒对象等待集合中（wait set）中的所有线程，这些线程被唤醒后，又会开始竞争对象的锁
 * 8.在某个时刻，只有唯一一个线程可以拥有对象的锁
 */
public class WaitAndNotifyAndNotifyAllTest {

    private int counter = 0;


    public synchronized void inc() throws InterruptedException {
        while (counter == 1){
            wait();
        }
        counter ++;
        System.out.println(counter);
        notifyAll();
    }

    public synchronized void sub() throws InterruptedException {
        while (counter == 0){
            wait();
        }
        counter--;
        System.out.println(counter);
        notifyAll();
    }


    /**
     * 返回的结果为101010101010.....
     * @param args
     */
    public static void main(String[] args) {
        WaitAndNotifyAndNotifyAllTest test = new WaitAndNotifyAndNotifyAllTest();
        new Thread(()->{
            try {
                while (true){
                    test.inc();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()->{
            try {
                while (true){
                    test.inc();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()->{
            try {
                while (true){
                    test.sub();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()->{
            try {
                while (true){
                    test.sub();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
