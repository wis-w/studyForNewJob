package juc;

import java.util.concurrent.TimeUnit;

/**
 * @author wyg_edu
 * @date 2020年5月21日 上午8:23:19
 * @version v1.0
 * 死锁是两个或者两个以上的进行在执行过程中
 * 因抢夺资源而造成的相互等待的现象
 * 若无外力推动则都将无法推动下去
 */
public class DeadLockDemo {

	static String lockA = "A";
	static String lockB = "B";
	public static void main(String[] args) {
		new Thread(new HoldLockThread(lockA, lockB), "ThreadAA").start();
		new Thread(new HoldLockThread(lockB, lockA), "ThreadBB").start();

	}
}

class HoldLockThread implements Runnable{
	
	private String lockA;
	private String lockB;
	
	public HoldLockThread(String lockA, String lockB) {
		super();
		this.lockA = lockA;
		this.lockB = lockB;
	}
	@Override
	public void run() {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName()+"\t持有"+lockA+"\t尝试获取"+lockB);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lockB) {
				System.out.println(Thread.currentThread().getName()+"\t持有"+lockB+"\t尝试获取"+lockA);
			}
		}
	}
	
}
