package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wyg_edu
 * @date 2020年4月30日 上午8:15:00
 * @version v1.0 题目：一个初始值为0的变量，两个线程交替操作，一个加1一个减1
 */
public class ProdConSumer_TraditionDemo {
	
	public static void main(String[] args) {
		ShareData shareData = new ShareData();
		new Thread(()->{
			for(int i=0;i<5;i++) {
				try {
					shareData.increment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"AA").start();
		
		new Thread(()->{
			for(int i=0;i<5;i++) {
				try {
					shareData.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"BB").start();
	}

}

class ShareData {
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment() throws Exception {
		lock.lock();
		try {
			// 1、判断
			while (number != 0) {// 多线程判断使用while ， 不适用if 避免虚假唤醒
				// 等待不能生产
				condition.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			// 通知唤醒
			condition.signal();
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
	}
	
	public void decrement() throws Exception {
		lock.lock();
		try {
			// 1、判断
			while (number == 0) {
				// 等待不能生产
				condition.await();
			}
			number--;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			// 通知喚醒
			condition.signal();
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
	}

	
}