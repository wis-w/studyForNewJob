package demo;

import java.util.concurrent.TimeUnit;

/**
 * @author wyg_edu
 * @date 2020年5月25日 上午8:18:26
 * @version v1.0
 */
public class Demo2 {
	public static void main(String[] args) {
		System.out.println("asdas");
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
		}
		synchronized (lockB) {
			System.out.println(Thread.currentThread().getName()+"\t持有"+lockB+"\t尝试获取"+lockA);
		}
	}
	
