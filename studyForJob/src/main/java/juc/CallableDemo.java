package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author wyg_edu
 * @date 2020年5月9日 上午8:09:30
 * @version v1.0
 * 多线程中获取新线程的方式
 */
public class CallableDemo {
	
	public static void main(String[] args) throws Exception {
		
		FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new MyThread());
		FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new MyThread());
		
		new Thread(futureTask1,"AA").start();
		new Thread(futureTask2,"BB").start();// 需要起两个， 否则第二个会直接复用第一个的结果
		
		int n1 = 100;
		int n2 = 0;
		while(!futureTask1.isDone()) {
			n2 = futureTask1.get();// 要求获得计算返回值，如果没有接收到返回值，则会导致阻塞知道计算完成  所以表达式一般放到最后执行
		}
		
		System.out.println("result:\t" + (n1 + n2));
	}

}

class MyThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("******* come in Callable");
		return 1024;
	}

}
