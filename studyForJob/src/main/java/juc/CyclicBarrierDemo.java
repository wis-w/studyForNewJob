package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wyg_edu
 * @date 2020年4月27日 上午8:14:18
 * @version v1.0
 * 线程减到0时启动后续进行与CountdownL。。相反
 */
public class CyclicBarrierDemo {
	
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(7,()->{
			System.out.println("最后的实现le ");
		}) ;
		
		for(int i =0 ;i<7;i++) {
			final int tem =i;
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"\t"+tem);
				try {
					barrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
		}
		
	}

}
