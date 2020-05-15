package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wyg_edu
 * @date 2020��4��27�� ����8:14:18
 * @version v1.0
 *字符集修改导致中文乱码
 */
public class CyclicBarrierDemo {
	
	public static void main(String[] args) {
		
//		CountDownLatch
		CyclicBarrier barrier = new CyclicBarrier(7,()->{
			System.out.println("���� ʵ��le ");
		}) ;
		
		for(int i =0 ;i<7;i++) {
			final int tem =i;
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"\t"+tem);
				try {
					barrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
		}
		
	}

}
