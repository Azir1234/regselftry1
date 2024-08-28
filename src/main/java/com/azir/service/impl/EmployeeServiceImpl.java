package com.azir.service.impl;

import com.azir.entity.Employee;
import com.azir.mapper.EmployeeMapper;
import com.azir.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee selectByUsername(String username) {
        QueryWrapper<Employee> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        //查询数据
      Employee employee= employeeMapper.selectOne(wrapper);
      return employee;
    }
}
