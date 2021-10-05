package cn.hhx.mybatis.dao;

import cn.hhx.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author hhxStellar
 * @date 2021/9/22-11:12
 */
public interface EmployeeDao {
    Employee selectOneEmployeeById(Integer id);

    //指名在mapper.xml文件中传入参数的key
    Employee selectOneEmployeeByIdAndName(@Param("id") Integer id, @Param("employeeName") String name);

    //用map传参
    Employee seletOneEmployeeByMap(Map<String, Object> map);

    void insertOneEmployee(Employee employee);

    void updateOneEmployee(Employee employee);

    boolean deleteOneEmployeeById(Integer id);

    List<Employee> selectAllEmployees();

    //key是列名，value是列值
    Map<String, Object> selectOneEmpByIdAndReturnMap(Integer id);

    //键是记录的主键，值是封装的bean，用此注解，指定哪一列的值是主键
    @MapKey("id")
    Map<Integer, Employee> selectAllEmployeesAndReturnMap();
}
