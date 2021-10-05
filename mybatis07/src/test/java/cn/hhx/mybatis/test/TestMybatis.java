package cn.hhx.mybatis.test;

import cn.hhx.mybatis.bean.Employee;
import cn.hhx.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;


/**
 * @author hhxStellar
 * @date 2021/9/28-19:48
 */
public class TestMybatis {
    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory;
    }

    @Test
    public void test1() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> employees = employeeMapper.selectAll();
            Iterator<Employee> iterator = employees.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } finally {
            sqlSession.close();
        }
    }
}
