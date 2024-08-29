package com.azir.service;

import com.azir.entity.Employee;

import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

public interface EmployeeService extends IService<Employee> {


    Employee selectByUsername(String username);

    void insertEmployee(Employee employee);


}
