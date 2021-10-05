package cn.hhx.mybatis.bean;

import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/9/23-15:30
 */
public class Department {
    private Integer id;
    private String deptName;
    private List<Employee> empList;

    public Department() {
    }

    public Department(String deptName) {
        this.deptName = deptName;
    }

    public Department(Integer id, String deptName) {
        this.id = id;
        this.deptName = deptName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employee> empList) {
        this.empList = empList;
    }

    public Department(Integer id, String deptName, List<Employee> empList) {
        this.id = id;
        this.deptName = deptName;
        this.empList = empList;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
