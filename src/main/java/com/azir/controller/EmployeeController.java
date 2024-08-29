package com.azir.controller;

import com.alibaba.druid.util.StringUtils;
import com.azir.common.R;
import com.azir.common.ThreadLocalParam;
import com.azir.entity.Employee;
import com.azir.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/employee")

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/login")
    public R<String> login(@RequestBody Employee employee, HttpSession session){
        //查询是否有一样的
        Employee employee1= employeeService.selectByUsername(employee.getUsername());
        //设置ThreadLocal
        //ThreadLocalParam.set(employee1.getId());

        session.setAttribute("employee",employee1);
        return R.success("nothing");
    }
    @PostMapping("/logout")
    public R<String> loginOut(HttpSession session){

        session.removeAttribute("employee");
        return R.success("退出成功");
    }

    @GetMapping("/page")
    public R<Page> pageSelect(Integer page, Integer pageSize,String name){
       System.out.println(""+page+pageSize+name);
        //分页构造器
        Page pageInfo=new Page(page,pageSize);
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.like(!StringUtils.isEmpty(name),Employee::getName,name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping
    public  R<String> insertEmployee(@RequestBody Employee employee,HttpSession session){

        Employee employee1= (Employee) session.getAttribute("employee");
        employee.setCreateUser(employee1.getId());
        employee.setUpdateUser(employee1.getId());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateTime(LocalDateTime.now());
        employee.setStatus(1);
        employee.setPassword("Sjh12345678");
        System.out.println(employee);
       Boolean b= employeeService.saveOrUpdate(employee);
       if(b){
           return R.success("添加员工成功");
       }
        else{
            return R.error("保持数据失败");
       }
    }

    @GetMapping("/{id}")
    public R<Employee> updateEmployee(@PathVariable("id")Long id){
        System.out.println(id);
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Employee employee= employeeService.getOne(queryWrapper);
        System.out.println(employee);
        return R.success(employee);
    }

    @PutMapping
    public R<String> changeEmployee( @RequestBody Employee employee,HttpSession session){
        System.out.println(employee);
        Employee employee1= (Employee) session.getAttribute("employee");


//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(employee1.getId());

        employeeService.saveOrUpdate(employee);
        return R.success("修改员工成功");
    }

}
