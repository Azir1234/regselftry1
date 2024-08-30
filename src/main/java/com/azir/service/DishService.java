package com.azir.service;

import com.azir.entity.Dish;
import com.azir.entity.DishDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DishService extends IService<Dish> {
    void saveDishDto(DishDto dishDto);
}
