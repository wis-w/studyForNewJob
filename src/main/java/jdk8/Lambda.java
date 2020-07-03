package jdk8;

import java.util.Comparator;

import org.junit.Test;

/**
 * @author wyg_edu
 * @date 2020年6月30日 上午8:25:32
 * @version v1.0
 */
public class Lambda {
	
	@Test
	public void test3() {
		System.out.println(getoperation(100, (x) -> x * x));
	}
	
	public Integer getoperation(Integer num, MyFun fun) {
		return fun.getValue(num);
	}
	
	@Test
	public void test1() {
		int num = 8;
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("11111"+num);
			}
		};
		r.run();
		
		Runnable r1 = ()->{
			System.out.println("222");
			};
		r1.run();
	}
	
	
	@Test
	public void test2() {
		Comparator<Integer> com = (x,y)->Integer.compare(x, y);
		System.out.println(com.compare(1, 2));
	}
	
	@Test
	public void testAdd() {
		System.out.println(test3(2L,3L,(x,y)->x*y));
	}
	
	public Long test3(Long l1,Long l2,MyFun2<Long, Long> mf) {
		return mf.testAdd(l1, l2);
		
	}

}
