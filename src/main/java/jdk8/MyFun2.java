package jdk8;
/**
 * @author wyg_edu
 * @date 2020年7月2日 上午8:23:15
 * @version v1.0
 */
@FunctionalInterface
public interface MyFun2<T, R> {
	
	R testAdd(T t1, T t2);
	
}
