package cn.hhx.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author hhxStellar
 * @date 2021/10/3-14:59
 */

/**
 * 插件用来拦截哪个对象的哪个方法
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)
})
public class MyFirstPlugin implements Interceptor {
    //1、拦截
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //执行目标方法
        System.out.println("interceptor方法可以获取被拦截的方法" + invocation.getMethod());
        Object target = invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        metaObject.setValue("parameterHandler.parameterObject", 11);
        System.out.println("sql用的参数是" + value);
        Object proceed = invocation.proceed();
        //返回执行后的返回值
        return proceed;
    }

    //2、包装目标对象，也就是为目标对象创建一个代理对象
    @Override
    public Object plugin(Object target) {
        System.out.println("plugin,四大对象创建的时候调用" + target);
        //创建动态代理
        Object wrap = Plugin.wrap(target, this);
        System.out.println(wrap);
        return wrap;
    }

    //将插件注册时的属性设置进来
    @Override
    public void setProperties(Properties properties) {
        System.out.println("设置插件信息" + properties);
    }
}
