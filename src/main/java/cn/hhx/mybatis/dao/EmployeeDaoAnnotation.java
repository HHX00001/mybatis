package cn.hhx.mybatis.dao;

import cn.hhx.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @author hhxStellar
 * @date 2021/9/20-16:59
 */
public interface EmployeeDaoAnnotation {
    @Select("select * from employee where id = #{id}")
    Employee selectOneEmployeeById(Integer id);
}
