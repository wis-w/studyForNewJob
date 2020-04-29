package juc;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wyg_edu
 * @date 2020��4��28�� ����8:20:16
 * @version v1.0
 * 阻塞队列
 * 
 */
public class BlockingQueueDemo {
	
	public static void main(String[] args) throws Exception {
		//List list = null;
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);
		blockingQueue.add("a");//   true
		//System.out.println(blockingQueue.add("d"));//异常
		
		System.out.println(blockingQueue.element());// 返回首元素
		
		System.out.println(blockingQueue.remove());// true
		//System.out.println(blockingQueue.remove());// 异常
		
		System.out.println(blockingQueue.offer("a"));//true
		System.out.println(blockingQueue.offer("a"));//越界false
		System.out.println(blockingQueue.poll());//a
		System.out.println(blockingQueue.poll());//越界取null
		
		//阻塞
		new Thread(()->{
			try {
				blockingQueue.take();// 如果没有取到元素则一直等待
				System.out.println("取出完成");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		blockingQueue.put("a");
		System.out.println("**************");
		blockingQueue.put("d");// 如果该元素满了，则进行阻塞等待
		System.out.println("结束插入");
		
		System.out.println(blockingQueue.offer("q", 2, TimeUnit.SECONDS));// 插入，如果队列满则等待2秒 超时返回false
		System.out.println("等待结束");
		
	}
	
}
