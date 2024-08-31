package com.azir.service.impl;

import com.azir.entity.ShoppingCart;
import com.azir.mapper.ShoppingCartMapper;
import com.azir.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
