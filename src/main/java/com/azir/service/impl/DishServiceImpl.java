package com.azir.service.impl;

import com.azir.entity.Dish;
import com.azir.entity.DishDto;
import com.azir.entity.DishFlavor;
import com.azir.mapper.DishMapper;
import com.azir.service.DishFlavorService;
import com.azir.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;
    @Override
    public void saveDishDto(DishDto dishDto) {
        this.save(dishDto);
        Long id= dishDto.getId();
        List<DishFlavor> flist=new ArrayList<>();
        for (DishFlavor dishFlavor : flist) {
            dishFlavor.setDishId(id);
        }
        dishFlavorService.saveBatch(flist);
    }
}
