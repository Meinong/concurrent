package com.huaizhu.concurrent;

/**
 * synchronized 修饰一个对象时：
 *  当使用synchronized 关键字来修饰代码块时，字节码层面上是通过monitorenter 和 monitorexit 指令来实现的锁的获取与释放动作
 *  当线程进入到synchronized 指令后 ，线程会持有monitor 对象，退出monitorenter 指令后，线程将会释放monitor对象
 * synchronized 修饰一个方法时:
 *  flag: ACC_PUBLIC ACC_SYNCHRONIZED
 *  jvm 使用ACC_SYNCHRONIZED 访问标志来区分一个方法是否是同步方法，如果有，那么执行线程会先持有方法所在对象的monitor对象
 * synchronized 修饰一个静态方法时：
 *  flag：ACC_PUBLIC ACC_STATIC ACC_SYNCHRONIZED
 *
 * JVM 中的同步是基于进入与退出监视器对象(管程对象) monitor 来实现的，每个对象实列 都会有一个monitor 对象，monitor 对象会和
 Java 对象一同创建并销毁
 *
 * 当多个线程同时访问一段同步代码时,这些线程会被放到一个叫EntryList集合中,处于阻塞状态的线程都会被放到该列表中，接下来，当线程获取到对象的
   monitor 时， monitor是依赖底层操作系统的mutex lock 来实现互斥的，线程获取mutex成功，则会持有该mutex,    这时其他的线程无法再获取到mutex
   在linux 下是通过 pthread_mutex_lock 函数实现的

 *
 * 总结：
 *  同步锁在这种实现方式中，因为monitor依赖与底层操作系统，这样就会存在用户态与内核态的切换，所有会增加性能开销
 */
public class Synchronized {
}
