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
 *  同步锁在这种实现方式中，因为monitor依赖与底层操作系统，这样就会存在用户态与内核态的切换，所以会增加性能开销
 *  通过java 对象头中一些标志位来实现如下： 对象头包含：1.Mark word 2.指向类的指针 3.数组长度
 *  其中Mark word：
 *   1）无锁标记
 *   2）偏向锁标记
 *   3）轻量级锁标记
 *   4）重量级标记
 *   5）GC 标记
 *  无锁:
 *  偏向锁：
 *    针对于一个线程来说的，它的主要作用就是优化同一个线程多次获取一个锁的情况，如果一个synchronized方法被一个线程访问，那么这个方法所在的对象
 *    就会在其mark word中的将偏向锁进行标记，同时还会有一个字段来存储该线程的ID,当这个线程再次访问同一个synchronized方法时，它会检查这个对象的
 *    Mark word 的偏向锁标记以及是否指向了其线程ID,如果是的话，那么该线程就无需再去进入管程（monitor）了，而是直接进入该方法体中
 *    如果是另外一个线程访问这个synchronized 方法时，如果获取失败,偏向锁会被取消掉
 *  轻量级锁：
 *    若第一个线程已经获取到了当前对象的锁，这时第二个线程又开始尝试争抢该对象的锁，由于该对象的锁已经被第一个线程获取到锁，因此它是偏向锁，而第二个线程
 *    在争抢时，会发现该对象头的中的Mark word已经是偏向锁，但里面存储的线程ID 不是自己（第一个线程）那么它会进行CAS,从而获取到锁
 *      1.如果获取锁成功，那么它会直接将Mark word中的线程ID由第一个线程变成自己（偏向锁标记位保持不变），这样该对象依然会保持偏向锁的状态
 *      2.获取锁失败，则表示这时可能会有多个线程同时尝试竞争该对象的锁，那么这时偏向锁就会进行升级，升级位轻量级锁
 *    通过自旋,如果自旋失败时，那么锁就会进入重量级锁，在这种情况下，无法获取到锁的线程都会进入到monitor(即：内核态)
 *  重量级锁：
 *   线程最终进入内核态
 */
public class Synchronized {
}
