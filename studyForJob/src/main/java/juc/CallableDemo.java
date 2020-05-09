package juc;

import java.util.concurrent.Callable;

/**
 * @author wyg_edu
 * @date 2020年5月9日 上午8:09:30
 * @version v1.0
 * 多线程中获取新线程的方式
 */
public class CallableDemo {

}

class MyThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		return null;
	}

}
