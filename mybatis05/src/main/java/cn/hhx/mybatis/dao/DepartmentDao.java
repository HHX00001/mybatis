package cn.hhx.mybatis.dao;

import cn.hhx.mybatis.bean.Department;

/**
 * @author hhxStellar
 * @date 2021/9/26-0:20
 */
public interface DepartmentDao {
    Department getDeptById(Integer id);
}
