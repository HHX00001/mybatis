package cn.hhx.mybatis.test;

import cn.hhx.mybatis.bean.Employee;
import cn.hhx.mybatis.dao.EmployeeDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @author hhxStellar
 * @date 2021/10/3-15:10
 */
public class TestPlugin {
    /**
     * 1、编写interceptor实现类
     * 2、使用@interceptor注解完成插件签名（也就是这个插件的信息，比如拦截对象的信息）
     * 3、将插件配置在全局文件中
     */

    @Test
    public void test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            Employee empById = mapper.getEmpById(1);
            System.out.println(empById);
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
            EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
            Page<Object> objects = PageHelper.startPage(1, 5);


            List<Employee> allEmployee = mapper.getAllEmployee();
            PageInfo<Employee> employeePageInfo = new PageInfo<>(allEmployee);
            for (Employee e : allEmployee) {
                System.out.println(e);
            }
            System.out.println("页码" + employeePageInfo.getPageNum());
            System.out.println("总记录" + employeePageInfo.getTotal());
            System.out.println("每页记录" + employeePageInfo.getPageSize());
            System.out.println("总页码" + employeePageInfo.getPages());
        } finally {
            sqlSession.close();
        }

    }
}
