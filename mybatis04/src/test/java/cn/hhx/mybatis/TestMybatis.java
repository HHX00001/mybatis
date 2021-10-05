package cn.hhx.mybatis;

import cn.hhx.mybatis.bean.Employee;
import cn.hhx.mybatis.dao.EmployeeDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/9/24-11:20
 */
public class TestMybatis {
    //动态where标签

    //测试if
    @Test
    public void test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            Employee employee = new Employee(null, null, "1", null);
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> employeeList = employeeDao.getEmpByConditionIf(employee);
            System.out.println(employeeList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test2() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee(null, "%星%", "1", null);
            List<Employee> emp = employeeDao.getEmpByConditionTrim(employee);
            System.out.println(emp);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test3() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            //分支只会带一个
            Employee employee = new Employee(null, null, null, null);
            List<Employee> employeeList = employeeDao.getEmpByConditionChoose(employee);
            for (Employee emp : employeeList) {
                System.out.println(emp);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test4() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee(9, "小明", "1", "321321@qq.com");
            employeeDao.updateEmp(employee);
            System.out.println(employeeDao.getEmpById(9));
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test5() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> empForEach = employeeDao.getEmpForEach(Arrays.asList(1, 2, 3, 4));
            for (Employee employee : empForEach) {
                System.out.println(employee);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test6() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> empList = new ArrayList<>();
            empList.add(new Employee("小兰1", "0", "231321@qq.com"));
            empList.add(new Employee("小兰2", "0", "234367221@qq.com"));
            empList.add(new Employee("小兰3", "0", "24324321@qq.com"));
            employeeDao.addEmps(empList);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test7() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> empList = mapper.getEmpTestInnerParam(new Employee());
            for (Employee emp : empList) {
                System.out.println(emp);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test8() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee();
            employee.setEmployeeName("兰");
            List<Employee> empList = mapper.getEmpTestInnerParam(employee);
            for (Employee emp : empList) {
                System.out.println(emp);
            }
        } finally {
            sqlSession.close();
        }
    }
}
