package cn.hhx.mybatis.test;

import cn.hhx.mybatis.bean.Employee;
import cn.hhx.mybatis.dao.EmployeeDao;
import cn.hhx.mybatis.dao.EmployeeDaoAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author hhxStellar
 * @date 2021/9/20-0:03
 */
public class MybatisTest {
    @Test
    public void testMybatis() throws IOException {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = sqlSession.selectOne("selectOneEmployeeById", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test2() throws IOException {
        String resource = "mybatis.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);//代理对象
            Employee employee = mapper.selectOneEmployeeById(1);//是非线程安全的，所以每次使用完要关闭
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test3() throws IOException {
        String resource = "mybatis.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            //基于注解的
            EmployeeDaoAnnotation mapper = sqlSession.getMapper(EmployeeDaoAnnotation.class);//代理对象
            Employee employee = mapper.selectOneEmployeeById(1);//是非线程安全的，所以每次使用完要关闭
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test4() throws IOException {
        String resource = "mybatis.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);//代理对象
            Employee emp = new Employee();
            emp.setEmployeeName("惠恒星");
            Employee employee = mapper.getEmp(emp);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }
}
