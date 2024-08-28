package com.azir.controller;

import com.azir.common.R;
import com.azir.entity.Employee;
import com.azir.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/login")
    public R<String> login(@RequestBody Employee employee, HttpSession session){
        //查询是否有一样的
        Employee employee1= employeeService.selectByUsername(employee.getUsername());
        System.out.println(employee1);
        session.setAttribute("yes",employee1.getUsername());
        return R.success("nothing");
    }
    @PostMapping("/logout")
    public R<String> loginOut(HttpSession session){
        System.out.println(session.getAttribute("yes"));
        session.removeAttribute("yes");
        return R.success("退出成功");
    }
}
