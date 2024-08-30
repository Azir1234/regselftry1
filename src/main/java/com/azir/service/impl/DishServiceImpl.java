package com.azir.service.impl;

import com.azir.entity.Dish;
import com.azir.mapper.DishMapper;
import com.azir.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
