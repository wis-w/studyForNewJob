package demo;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wyg_edu
 * @date 2020年5月12日 下午5:47:09
 * @version v1.0
 */
public class Demo {
	
	private static final String[] STR = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	private static final int LEN = STR.length;
	private static volatile long index = 0;

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		System.out.println("start\t"+new Date());
		try {
			for (int i = 0; i < 10; i++) {
				executorService.execute(()->{
					ergodic();
				});
			}
		} catch (Exception e) {
		}finally {
			executorService.shutdown();
		}
	}

	/**
	 * 做密码判断
	 */
	private static void ergodic() {
		String pwd = "zxsas";
		AtomicReference<String> rs = new AtomicReference<String>();
		while (!pwd.equals(rs)) {
			rs = new AtomicReference<String>(doString(index, pwd.length()));
			index++;
		}
		System.out.println(rs+"\t"+new Date());
		return ;
	}

	/**
	 * 执行密码运算
	 * 
	 * @param flag
	 * @param n
	 */
	private static String doString(long flag, int n) {
		StringBuilder sb = new StringBuilder() ;
		try {
			sb = new StringBuilder(STR[(int) (flag / power(LEN, n - 1))]);
		} catch (Exception e) {
			System.out.println(flag+"///");
		}
		for (int i = n - 2; i >= 0; i--) {
			sb.append(STR[(int) (flag / power(LEN, i) % LEN)]);
		}
		return sb.toString();
	}

	/**
	 * 次幂运算
	 * 
	 * @param num
	 * @param pod
	 * @return
	 */
	private static long power(int num, int pod) {
		long rs = 1;
		for (int i = 0; i < pod; i++) {
			rs *= num;
		}
		return rs;
	}

}