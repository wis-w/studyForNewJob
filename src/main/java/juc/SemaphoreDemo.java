package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author wyg_edu
 * @date 2020年4月27日 上午8:22:53
 * @version v1.0
 */
public class SemaphoreDemo {
	
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		
		for(int i=0;i<6;i++) {
			new Thread(()->{
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName()+"\t 占据位置-------");
					TimeUnit.SECONDS.sleep(3);
					System.out.println(Thread.currentThread().getName()+"\t 离开位置");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			},String.valueOf(i)).start();
		}
	}

}
