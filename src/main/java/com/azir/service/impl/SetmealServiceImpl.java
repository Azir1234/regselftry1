package com.azir.service.impl;

import com.azir.entity.Setmeal;
import com.azir.mapper.SetMealMapper;
import com.azir.service.SetmealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetmealService {
}
