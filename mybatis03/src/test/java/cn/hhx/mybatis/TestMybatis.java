package cn.hhx.mybatis;

import cn.hhx.mybatis.bean.Department;
import cn.hhx.mybatis.bean.Employee;
import cn.hhx.mybatis.dao.DepartmentDao;
import cn.hhx.mybatis.dao.EmployeeDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author hhxStellar
 * @date 2021/9/23-15:05
 */
public class TestMybatis {

    //resultMap自定义数据库与javaBean的映射规则
    @Test
    public void test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee emp = employeeDao.getEmpById(1);
            System.out.println(emp);
        } finally {
            sqlSession.close();
        }
    }

    //一对一非分段查询
    @Test
    public void test2() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.getEmpAndDeptById(1);
            System.out.println(employee);
            System.out.println(employee.getDept());
        } finally {
            sqlSession.close();
        }
    }

    //一对一分段查询 懒加载
    @Test
    public void test3() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            //分段查询 延迟加载lazyLoadingEnabled = true
            Employee employee = employeeDao.getEmpAndDeptByIdByStep(1);
            //按需加载 aggressiveLazyLoading = false
            //不用部门信息就不会去查部门
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    //一对多关系,分段查询，懒加载，局部懒加载
    @Test
    public void test4() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            DepartmentDao departmentDao = sqlSession.getMapper(DepartmentDao.class);
            Department deptAndEmp = departmentDao.getDeptAndEmp(1);
            System.out.println(deptAndEmp.getDeptName());
            System.out.println(deptAndEmp);
        } finally {
            sqlSession.close();
        }
    }

    //使用鉴别器
    @Test
    public void test5() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.getEmpAndDeptByIdDis(1);
            System.out.println(employee.getEmail());
            System.out.println(employee.getDept());
        } finally {
            sqlSession.close();
        }
    }
}
