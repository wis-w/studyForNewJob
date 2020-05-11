package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wyg_edu
 * @date 2020年5月11日 上午8:16:35
 * @version v1.0
 * 线程池作用，线程复用，减少资源开销，利于管理
 */
public class MyThreadPoolDemo {
	
	public static void main(String[] args) {
		
		ExecutorService threadPool = Executors.newFixedThreadPool(5);// 一池5个处理线程
		// 模拟10个用户处理业务
		try {
			for(int i=1;i<=10;i++) {
				threadPool.execute(()->{
					System.out.println(Thread.currentThread().getName()+"\t 办理业务");
				});
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			threadPool.shutdown();
		}
		
	}

}
