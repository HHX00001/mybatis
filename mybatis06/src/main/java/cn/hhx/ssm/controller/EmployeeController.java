package cn.hhx.ssm.controller;

import cn.hhx.ssm.bean.Employee;

import cn.hhx.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/9/27-21:28
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @ResponseBody
    @RequestMapping("/employeeList")
    public ModelAndView getAllEmp() {
        ModelAndView modelAndView = new ModelAndView();
        List<Employee> employees = employeeService.empList();
        modelAndView.addObject("empList", employees);
        modelAndView.setViewName("empList");
        return modelAndView;
    }
}
