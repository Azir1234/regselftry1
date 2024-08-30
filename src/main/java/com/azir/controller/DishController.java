package com.azir.controller;

import com.alibaba.druid.util.StringUtils;
import com.azir.common.R;
import com.azir.entity.Dish;
import com.azir.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @GetMapping("/page")
    public R<Page> dishPage(Integer page,Integer pageSize,String name){
        System.out.println(""+page+pageSize+name);
        //分页构造器
        Page pageInfo=new Page<>(page,pageSize);

        LambdaQueryWrapper<Dish> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(name),Dish::getName,name);
        lambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping("/status/{choice}")
    public R<String> dishStatus(@PathVariable Integer choice,Long...ids){
        if((int)choice==0){
            //停售
            UpdateWrapper<Dish> updateWrapper=new UpdateWrapper<>();
            updateWrapper.set("status",0);
            updateWrapper.in("id",ids);
            dishService.update(updateWrapper);
        }
        if((int)choice==1)
        {
            UpdateWrapper<Dish> updateWrapper=new UpdateWrapper<>();
            updateWrapper.set("status",1);
            updateWrapper.in("id",ids);
            dishService.update(updateWrapper);
        }
        return R.success("启停成功");
    }
    @DeleteMapping
    public R<String> delete(Long...ids){
        /*UpdateWrapper<Dish> updateWrapper=new UpdateWrapper<>();
        updateWrapper.in("id",ids);*/

        QueryWrapper<Dish> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("id",ids);
        dishService.remove(queryWrapper);
        return R.success("删除成功");
    }
}
