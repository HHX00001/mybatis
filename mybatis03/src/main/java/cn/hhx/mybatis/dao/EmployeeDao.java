package cn.hhx.mybatis.dao;

import cn.hhx.mybatis.bean.Employee;

import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/9/23-15:06
 */
public interface EmployeeDao {
    Employee getEmpById(Integer id);

    //查询员工和对应的部门信息
    Employee getEmpAndDeptById(Integer id);

    Employee getEmpAndDeptByIdByStep(Integer id);

    List<Employee> getEmpListByDeptId(Integer deptId);

    //用鉴别器查询
    Employee getEmpAndDeptByIdDis(Integer id);
}
