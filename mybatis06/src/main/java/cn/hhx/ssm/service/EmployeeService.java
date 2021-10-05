package cn.hhx.ssm.service;

import cn.hhx.ssm.bean.Employee;
import cn.hhx.ssm.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/9/27-21:12
 */
@Service()
public class EmployeeService {
    @Autowired
    EmployeeDao employeeDao;

    public List<Employee> empList() {
        List<Employee> employeeList = employeeDao.getAllEmployee();
        return employeeList;
    }
}
