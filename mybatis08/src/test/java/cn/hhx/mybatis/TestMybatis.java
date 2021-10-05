package cn.hhx.mybatis;

import cn.hhx.mybatis.bean.Employee;
import cn.hhx.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/9/28-20:29
 */
public class TestMybatis {

    /**
     * 实现原理：
     * 1、获取SqlSessionFactory实例:
     * 解析inputstream成一个configuration对象，并将config对象作为build的参数，返回一个defaultSqlSessionFactory
     * 其中configuration对象的mappedStatement属性代表一个增删改查的详细信息
     * <p>
     * 2、调用SqlSessionFactory方法获取SqlSession实例
     * 返回一个defaultsqlsession对象包含了excutor和全局configuration
     * <p>
     * 3、获取接口的代理类（MapperProxy）
     * defaultsqlsession.getMapper获得一个mapperproxy对象包含（defaultsqlsession（包含了excutor和全局configuration））
     * 4、执行代理类的增删改查
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取会话工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //获取会话
        SqlSession sqlSession = sessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            //获取mapper的代理类对象:org.apache.ibatis.binding.MapperProxy@6acdbdf5
            System.out.println(employeeMapper);
            System.out.println("---------");
            //
            Employee employee = employeeMapper.selectByPrimaryKey(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }
}
