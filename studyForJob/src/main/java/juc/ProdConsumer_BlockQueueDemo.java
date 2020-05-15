package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wyg_edu
 * @date 2020年5月8日 上午8:11:17
 * @version v1.0 volatile/CAS/atmicInteger.BlockQueue/线程交互/原子引用 串讲
 */
public class ProdConsumer_BlockQueueDemo {
	public static void main(String[] args) throws Exception {
		MyRource  myRource = new MyRource(new ArrayBlockingQueue<String>(10));
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"\t 生产启动" );
			try {
				myRource.myProd();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"prod").start();
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"\t消费启动" );
			try {
				myRource.myConsumer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"cousm").start();
		TimeUnit.SECONDS.sleep(5);
		System.out.println("活动结束了");
		myRource.stop();
	}
}

class MyRource {
	private volatile boolean FLAG = true;// 默认开启
	private AtomicInteger atomicInteger = new AtomicInteger();

	BlockingQueue<String> blockingQueue = null;

	public MyRource(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
		System.out.println(blockingQueue.getClass().getName());
	}

	public void myProd() throws Exception {
		String data = null;
		boolean retValue = true;
		while (FLAG) {
			data = atomicInteger.incrementAndGet() + "";// i++
			blockingQueue.offer(data, 2, TimeUnit.SECONDS);
			if (retValue) {
				System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");
			} else {
				System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
			}
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(Thread.currentThread().getName() + "\t停止生产");
	}

	public void myConsumer() throws Exception {
		String result = null;
		while (FLAG) {
			result = blockingQueue.poll(2, TimeUnit.SECONDS);
			if (null == result || "".equalsIgnoreCase(result)) {
				FLAG = false;
				System.out.println(Thread.currentThread().getName()+"\t 超过两秒钟没有取到，消费退出");
				return;
			}
			System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
		}
		
		
	}
	public void stop() {
		FLAG = false;
	}
}
