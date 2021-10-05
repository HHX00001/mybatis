package cn.hhx.mybatis.bean;

import java.io.Serializable;

/**
 * @author hhxStellar
 * @date 2021/9/26-0:18
 */
public class Department implements Serializable {

    private static final long serialVersionUID = 2L;

    private Integer id;
    private String deptName;

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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
