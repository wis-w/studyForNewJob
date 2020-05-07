package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wyg_edu
 * @date 2020年5月7日 上午8:19:10
 * @version v1.0
 * 多线程按顺序调用
 */
public class SyncAndReenTrantLockDemo {
	
	public static void main(String[] args) {
		
	}

}

class shareResource {

	private int number = 1;

	private Lock lock = new ReentrantLock();

	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();

	public void print(int n) {
		lock.lock();
		try {
			while (number != 1) {// 判断
				c1.await();
			}
			for (int i = 0; i < n; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			number = 2;// 修改标志位
			c2.signal();// 通知指定线程
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}