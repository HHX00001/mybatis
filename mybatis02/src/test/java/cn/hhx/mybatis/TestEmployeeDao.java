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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hhxStellar
 * @date 2021/9/22-11:17
 */
public class TestEmployeeDao {

    //查询
    @Test
    public void test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.selectOneEmployeeById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    //插入
    @Test
    public void test2() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee("惠恒星", "1", "1739173733@qq.com");
            employeeDao.insertOneEmployee(employee);
            System.out.println(employee.getId());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    //修改
    @Test
    public void test3() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            employeeDao.updateOneEmployee(new Employee(1, "惠恒星", "1", "1739173733@qq.com"));
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    //删除
    @Test
    public void test4() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            boolean isDelete = employeeDao.deleteOneEmployeeById(3);
            System.out.println("删除结果：" + isDelete);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    //传入两个参数的查询
    @Test
    public void test5() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            //多参数要在接口上写注解
            Employee employee = employeeDao.selectOneEmployeeByIdAndName(5, "惠恒星");
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    //用map传值
    @Test
    public void test6() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 5);
            map.put("employeeName", "惠恒星");
            map.put("tableName", "employee");//${}的用法
            Employee employee = employeeDao.seletOneEmployeeByMap(map);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    //返回的是一个list
    @Test
    public void test7() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> employeeList = employeeDao.selectAllEmployees();
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        } finally {
            sqlSession.close();
        }
    }

    //返回值是一个map的处理
    @Test
    public void test8() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Map<String, Object> empMap = employeeDao.selectOneEmpByIdAndReturnMap(2);
            System.out.println(empMap);
        } finally {
            sqlSession.close();
        }
    }

    //测试将多条数据封装成一个map，map的键是接口方法注释指定的列值
    @Test
    public void test9() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Map<Integer, Employee> employeesMap = employeeDao.selectAllEmployeesAndReturnMap();
            System.out.println(employeesMap);
        } finally {
            sqlSession.close();
        }
    }
}
