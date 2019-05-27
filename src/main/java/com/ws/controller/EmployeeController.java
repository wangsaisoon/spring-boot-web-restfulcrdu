package com.ws.controller;

import com.ws.dao.DepartmentDao;
import com.ws.dao.EmployeeDao;
import com.ws.entities.Department;
import com.ws.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    /**
     * 查询所有员工
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        // 放在请求域中
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    /**
     * 去添加页面
     * @return
     */
    @GetMapping("/emp")
    public String toAdd(Model model) {
        // 查询所有的部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/edit";
    }

    /**
     * 添加
     * SpringMVC自动将请求参数和入参对象的属性一一绑定
     *
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        // redirect:重定向到一个地址 / 代表当前项目路径
        // forward: 转发到一个地址
        System.out.println("员工信息：" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 去修改页面
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEdit(@PathVariable("id") Integer id, Model model) {
        System.out.println("id-->" + id);
        Employee employee = employeeDao.get(id);
        System.out.println("员工信息：" + employee);
        model.addAttribute("employee", employee);
        // 查询所有的部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/edit";
    }

    /**
     * 修改
     * @return
     */
    @PutMapping("/emp")
    public String editEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/emp/{id}")
    public String delEmp(@PathVariable("id") Integer id) {
        System.out.println("id-->" + id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
