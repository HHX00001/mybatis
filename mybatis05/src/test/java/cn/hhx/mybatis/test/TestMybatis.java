package cn.hhx.mybatis.test;


import cn.hhx.mybatis.bean.Employee;
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
 * @date 2021/9/25-20:47
 */
public class TestMybatis {
    public SqlSessionFactory getSqlSessionFactory() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            return sessionFactory;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //一级缓存，同一会话中
    @Test
    public void test() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.getEmpById(1);
            Employee employee1 = employeeDao.getEmpById(1);
            System.out.println("第一个的hash值是:" + employee.hashCode());
            System.out.println("第二个的hash值是:" + employee1.hashCode());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 总共两级缓存：
     * 一级缓存：（本地缓存）sqlsession级别，一个sqlsession对应一个一级缓存,存放一个map用来缓存数据
     * key:hashCode+查询的SqlId+编写的sql查询语句+参数
     * 一级缓存失效情况
     * 1、sqlsession不同
     * 2、查询条件不同
     * 3、两次查询中间还有别的增删改操作
     * 4、手动清空缓存
     * <p>
     * 与数据库一次会话获得的数据储存在本地
     * 以后要取得相同的数据，直接从缓存中拿，没必要去查询数据库
     */


    //测试一级缓存,sqlsession不同
    @Test
    public void test1() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        try {
            //两个不同的sqlsession 1
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            EmployeeDao employeeDao1 = sqlSession1.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.getEmpById(1);
            System.out.println(employee);
            Employee employee1 = employeeDao1.getEmpById(1);
            System.out.println(employee1);
            //相同的sqlsession中的两个对象的hash相同，也就是说都属于同一个对象 1
            //不同的sqlsession则不同 1
            System.out.println("第一个的hash值是:" + employee.hashCode());
            System.out.println("第二个的hash值是:" + employee1.hashCode());
        } finally {
            sqlSession.close();
            sqlSession1.close();
        }
    }

    //测试一级缓存，两次查询中间有别的操作
    @Test
    public void test2() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.getEmpById(1);
            employeeDao.addEmpById(new Employee(null, "小紫", "0", "3412321@qq.com"));
            Employee employee1 = employeeDao.getEmpById(1);
            //select的对象不同
            System.out.println("第一个的hash值是:" + employee.hashCode());
            System.out.println("第二个的hash值是:" + employee1.hashCode());
        } finally {
            sqlSession.close();
        }
    }

    //测试清空sqlsession中的缓存
    @Test
    public void test3() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.getEmpById(1);
            sqlSession.clearCache();
            Employee employee1 = employeeDao.getEmpById(1);
            System.out.println("第一个的hash值是:" + employee.hashCode());
            System.out.println("第二个的hash值是:" + employee1.hashCode());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 二级缓存：（全局缓存）基于namespace级别的缓存：一个namespace对应一个二级缓存
     * 工作机制：
     * 1、一个session，查询到一个数据，这个数据就会被放在当前会话的一级缓存中
     * 2、如果会话关闭，一级缓存的数据会被保存到二级缓存中；新的会话查询就会去二级缓存取值
     * 3、不同namespace查出的数据会放在自己的缓存中（map）
     * 效果：查出的数据默认先放在一级缓存中
     * 当会话关闭或提交后，会将一级缓存中的数据放进二级缓存
     *
     * <p>
     * 使用：
     * 1、开启全局二级缓存<setting name="cacheEnabled" value="true"/>
     * 2、去mapper.xml中配置使用二级缓存<cache></cache>
     * 3、pojo需要实现序列化接口
     * <p>
     * 和缓存相关的属性:
     * 1、<setting name="cacheEnabled" value="true"/>可以开关二级缓存，对一级缓存无影响，默认开启
     * 2、mapper中的select标签的"useCache="false""属性,对一级缓存无影响，影响二级缓存
     * 3、每个增删改标签有flushCache="true"属性，默认为true，完成之后会清空一级和二级缓存
     *      查询标签中为false
     * 4、sqlSession.clearCache();说sqlsession对象调用，只清空一级缓存
     * 5、localCacheScope(SESSION | STATEMENT)设置一级缓存作用域:STATEMENT禁用一级缓存
     */

    //测试二级缓存
    @Test
    public void test4() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        try {
            //用两次会话进行相同的查询
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            EmployeeDao employeeDao1 = sqlSession1.getMapper(EmployeeDao.class);
            Employee emp = employeeDao.getEmpById(1);
            sqlSession.close();

            //是从二级缓存中拿的，只发送了一次sql语句
            Employee emp1 = employeeDao1.getEmpById(1);
            sqlSession1.close();
            System.out.println("第一个hashcode值为：" + emp.hashCode());
            System.out.println("第二个hashcode值为：" + emp1.hashCode());
        } finally {

            sqlSession1.close();
        }
    }

}
