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
		ShareResource resource = new ShareResource();
		new Thread(()->{
			for (int i = 0; i < 5; i++) {
				resource.work("c1");
			}
		},"AA").start();
		new Thread(()->{
			for (int i = 0; i < 5; i++) {
				resource.work("c2");
			}
		},"BB").start();
		new Thread(()->{
			for (int i = 0; i < 5; i++) {
				resource.work("c3");
			}
		},"CC").start();
	}

}

class ShareResource {

	private int number = 1;

	private Lock lock = new ReentrantLock();

	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();

	public void work(String str) {

		switch (str) {
		case "c1":
			print(c1, c2, 1, 2);
			break;
		case "c2":
			print(c2, c3, 2, 3);
			break;
		case "c3":
			print(c3, c1, 3, 1);
			break;
		default:
			break;
		}
	}

	public void print(Condition wait, Condition done, int flag, int aim) {
		lock.lock();
		try {
			while (number != flag) {// 判断
				wait.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			number = aim;// 修改标志位
			done.signal();// 通知指定线程
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
	}

}