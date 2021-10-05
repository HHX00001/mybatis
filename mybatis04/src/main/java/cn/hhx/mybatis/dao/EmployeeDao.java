package cn.hhx.mybatis.dao;

import cn.hhx.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/9/24-13:18
 */
public interface EmployeeDao {

    Employee getEmpById(Integer id);

    //测试if标签
    List<Employee> getEmpByConditionIf(Employee employee);

    //与if一起使用的trim标签
    List<Employee> getEmpByConditionTrim(Employee employee);

    //choose标签
    List<Employee> getEmpByConditionChoose(Employee employee);

    void updateEmp(Employee employee);

    //传参封装成一个map，map的key就是"@Param("idList")"，map的value就是"List<Integer> idList"
    //不管传的是数组还是集合，最后传过去时都封装为map类型对象，map的key可能是collection，list，array，value才是真正的参数
    //而foreach中的collection属性值就是map的key
    //建议使用@param注解，这样传过去的map的key心里有数，
    List<Employee> getEmpForEach(@Param("idList") List<Integer> idList);

    void addEmps(@Param("empList") List<Employee> empList);

    //测试内置参数
    List<Employee> getEmpTestInnerParam(@Param("emp") Employee emp);
}
