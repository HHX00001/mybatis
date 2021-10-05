package cn.hhx.mybatis.dao;

import cn.hhx.mybatis.bean.Department;

/**
 * @author hhxStellar
 * @date 2021/9/23-16:13
 */
public interface DepartmentDao {
    Department getDeptById(Integer id);

    Department getDeptAndEmp(Integer id);
}
