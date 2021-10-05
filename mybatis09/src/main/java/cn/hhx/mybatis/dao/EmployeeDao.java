package cn.hhx.mybatis.dao;

import cn.hhx.mybatis.bean.Employee;

import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/10/3-15:37
 */
public interface EmployeeDao {
    Employee getEmpById(Integer id);

    List<Employee> getAllEmployee();
}
