package com.azir.service.impl;

import com.azir.entity.Dish;
import com.azir.entity.Setmeal;
import com.azir.entity.SetmealDish;
import com.azir.entity.SetmealDto;
import com.azir.mapper.SetMealMapper;
import com.azir.service.DishService;
import com.azir.service.SetmealDishService;
import com.azir.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class SetmealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;

    @Override
    public void savemeal(SetmealDto setmealDto) {
        this.save(setmealDto);
        Long id=setmealDto.getId();
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(id);
        }
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public SetmealDto getSetmealDto(Long id) {
        //先获取套餐
        SetmealDto setmealDto=new SetmealDto();
        Setmeal setmeal=  this.getById(id);

        BeanUtils.copyProperties(setmeal,setmealDto);

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("setmeal_id",id);
        List<SetmealDish> list = setmealDishService.list(queryWrapper);

        setmealDto.setSetmealDishes(list);
        return setmealDto;
    }

    @Override
    public SetmealDto updateSetmealDto(SetmealDto setmealDto) {
        Setmeal setmeal=new Setmeal();
        BeanUtils.copyProperties(setmealDto,setmeal);
        this.updateById(setmeal);


        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("setmeal_id",setmealDto.getId());
        setmealDishService.remove(queryWrapper);


        List<SetmealDish> setmealDishes=setmealDto.getSetmealDishes();

        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealDto.getId());
            setmealDishService.save(setmealDish);
        }

        setmealDto.setSetmealDishes(setmealDishes);

        return setmealDto;
    }
}
