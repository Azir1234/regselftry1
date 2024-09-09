package com.azir.controller;

import com.alibaba.druid.util.StringUtils;
import com.azir.common.R;
import com.azir.entity.Orders;
import com.azir.service.OrdersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Transactional
@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping("/submit")
    public R<String> submitOrder(@RequestBody Orders orders, HttpSession session){
        System.out.println(orders);
        orders.setStatus(2);
        Long userId= (Long) session.getAttribute("user");
        orders.setNumber(UUID.randomUUID().toString());
        orders.setUserId(userId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setPayMethod(1);

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);

        //总金额
        List<Orders> ordersList= ordersService.list(queryWrapper);
        BigDecimal sumAmout=BigDecimal.ZERO;
        for (Orders v : ordersList) {
           sumAmout= sumAmout.add(v.getAmount());
        }
        orders.setAmount(sumAmout);
        ordersService.save(orders);

        return R.success("保存成功");
    }
    @GetMapping("/userPage")
    public R<Page> pageOrder(Integer page,Integer pageSize,HttpSession session){
        Long userId= (Long) session.getAttribute("user");

        Page pageInfo=new Page(page,pageSize);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);

        ordersService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);

    }

    @GetMapping("/page")
    public R<Page> backendPage(Integer page,Integer pageSize,String number,LocalDateTime beginTime,LocalDateTime endTime){
        Page pageInfo=new Page(page,pageSize);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like(!StringUtils.isEmpty(number),"number",number);
        queryWrapper.between(beginTime!=null&&endTime!=null,"order_time",beginTime,endTime);
        /*queryWrapper.ge(beginTime!=null,"order_time",beginTime);
        queryWrapper.le(endTime!=null,"order_time",endTime);*/
        ordersService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
}
