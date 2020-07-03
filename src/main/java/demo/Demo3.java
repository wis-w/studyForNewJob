package demo;
/**
 * @author wyg_edu
 * @date 2020年6月28日 下午3:30:41
 * @version v1.0
 */
public class Demo3 {
	
	public static void main(String[] args) {
		new Thread(()->{
			System.out.println(Thread.currentThread().getName());
		},"AAA").start(); 
	}
}
