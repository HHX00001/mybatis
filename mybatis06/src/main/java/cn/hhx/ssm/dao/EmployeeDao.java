package cn.hhx.ssm.dao;

import cn.hhx.ssm.bean.Employee;
import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/9/26-22:40
 */
public interface EmployeeDao {

    List<Employee> getAllEmployee();
}
