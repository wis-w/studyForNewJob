package jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author wyg_edu
 * @date 2020年6月29日 上午8:27:05
 * @version v1.0
 */
public class EmployeeLamda {
	
	List<Employee> list = Arrays.asList(
			new Employee(1,"张三",18,"北京"),
			new Employee(2,"李四",28,"上海"),
			new Employee(3,"王五",38,"广州"),
			new Employee(4,"赵六",48,"深圳")
			);
	
	@Test
	public void test() {
		list.forEach(System.out::print);
	}
	
	@Test
	public void test2() {
		List<Employee> list2 = getEmp(new FilterEmp<Employee>() {
			@Override
			public boolean tese(Employee t) {
				return t.getAge()>28;
			}
		});
		
		list2.forEach(System.out::print);
	}
	
	public List<Employee> getEmp(FilterEmp<Employee> filterEmp) {
		List<Employee> rs = new ArrayList<Employee>();
		for(Employee emp : list) {
			if(filterEmp.tese(emp)) {
				rs.add(emp);
			}
		}
		return rs;
	}
	

}
