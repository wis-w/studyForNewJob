package juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wyg_edu
 * @date 2020年4月29日 上午8:31:59
 * @version v1.0
 * 阻塞队列SynchronsQueue
 * 同步队列不存储，存入一个消费一个
 */

public class SynchronsQueueDemo {
		
	public static void main(String[] args) {
		BlockingQueue<String> blockingQueue = new SynchronousQueue<String>();
		new Thread(()->{
			try {
				System.out.println(Thread.currentThread().getName()+"\t +a");
				blockingQueue.put("a");
				System.out.println(Thread.currentThread().getName()+"\t +b");
				blockingQueue.put("b");
				System.out.println(Thread.currentThread().getName()+"\t +c");
				blockingQueue.put("c");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"AA").start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println(Thread.currentThread().getName()+"\t take");
				blockingQueue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"BB").start();
		
	}

}
