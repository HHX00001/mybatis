package cn.hhx.mybatis.bean;

import java.io.Serializable;

/**
 * @author hhxStellar
 * @date 2021/9/25-20:42
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String employeeName;
    private String gender;
    private String email;

    public Employee() {
    }

    public Employee(Integer id, String employeeName, String gender, String email) {
        this.id = id;
        this.employeeName = employeeName;
        this.gender = gender;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
