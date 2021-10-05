package cn.hhx.mybatis.dao;

import cn.hhx.mybatis.bean.Employee;

/**
 * @author hhxStellar
 * @date 2021/9/20-0:53
 */
public interface EmployeeDao {
    Employee selectOneEmployeeById(Integer id);

    Employee getEmp(Employee employee);
}
