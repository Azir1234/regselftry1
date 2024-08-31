package com.azir.service.impl;

import com.azir.entity.Orders;
import com.azir.mapper.OrdersMapper;
import com.azir.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
}
