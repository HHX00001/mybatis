package cn.hhx.mybatis.test;

import cn.hhx.mybatis.bean.Employee;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author hhxStellar
 * @date 2021/9/25-21:11
 */
public class MyTest {
    @Test
    public void test() {
        Employee employee = new Employee();
        Employee employee1 = new Employee();
        System.out.println(employee.hashCode());
        System.out.println(employee1.hashCode());
        HashMap<Object, Object> map = new HashMap<>();
        map.put(employee, "hhh");
        map.put(employee1, "hdsaj");
        System.out.println(map.get(employee));
        System.out.println(map.get(employee1));
    }
}
