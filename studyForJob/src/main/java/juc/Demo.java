package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wyg_edu
 * @date 2020年5月12日 下午5:47:09
 * @version v1.0
 */
public class Demo {

	public static volatile AtomicInteger i= new AtomicInteger(0);
	private static String [] str= {"a","b"};
	private static int a = str.length;
	
	public static void main(String[] args) {
		long a = 62;
		System.out.println(a*a*a*a*a*a*a*a);
		System.out.println(Long.MAX_VALUE);
	}

	private static void test() {
			// TODO Auto-generated method stub
		String rs = "aaa";
		String t = "";
		while(t.equals(rs)) {
			doString(3);
			i.getAndIncrement();
		}
	}
private static StringBuilder sb = new StringBuilder();
	private static void doString(int falg) {
		for(int b = falg-1;b>=0;b--) {
		}
	}
}