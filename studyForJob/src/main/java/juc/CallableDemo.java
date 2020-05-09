package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wyg_edu
 * @date 2020年5月9日 上午8:09:30
 * @version v1.0
 * 多线程中获取新线程的方式
 */
public class CallableDemo {
	
	public static void main(String[] args) throws Exception {
		
		FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread());
		
		Thread t1 = new Thread(futureTask,"AAA");
		t1.start();
		
		System.out.println(futureTask.get());
	}

}

class MyThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("*******");
		return 1024;
	}

}
