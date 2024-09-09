package com.azir.service.impl;

import com.azir.entity.Dish;
import com.azir.entity.DishDto;
import com.azir.entity.DishFlavor;
import com.azir.mapper.DishMapper;
import com.azir.service.DishFlavorService;
import com.azir.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
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

    @Override
    public void updateDishDto(DishDto dishDto) {
        Dish dish=new Dish();
        BeanUtils.copyProperties(dishDto,dish);
        this.updateById(dish);

        List<DishFlavor> flavors = dishDto.getFlavors();
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("dish_id",dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dishDto.getId());
        }
        dishFlavorService.saveBatch(flavors);

    }

    @Override
    public DishDto getDishDto(Long id) {
        //先获取菜
        Dish dish=  this.getById(id);
        DishDto dishDto=new DishDto();

        BeanUtils.copyProperties(dish,dishDto);

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("dish_id",id);
        List<DishFlavor> list = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(list);


        return dishDto;
    }
}
