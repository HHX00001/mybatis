package cn.hhx.mybatis.dao;

import cn.hhx.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * @author hhxStellar
 * @date 2021/9/25-20:45
 */
public interface EmployeeDao {
    Employee getEmpById(Integer id);

    void addEmpById(@Param("emp") Employee employee);
}
