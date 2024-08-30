package com.azir.service.impl;

import com.azir.entity.DishFlavor;
import com.azir.mapper.DishFlavorMapper;
import com.azir.service.DishFlavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
