package com.azir.controller;

import com.alibaba.druid.util.StringUtils;
import com.azir.common.R;
import com.azir.entity.Setmeal;
import com.azir.entity.SetmealDish;
import com.azir.entity.SetmealDto;
import com.azir.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/setmeal")
@RestController
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @GetMapping("/page")
    public R<Page> setmealPage(Integer page,Integer pageSize,String name){
        Page pageInfo=new Page(page,pageSize);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like(!StringUtils.isEmpty(name),"name",name);
        setmealService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @DeleteMapping
    public R<String> setmealDelete(Long...ids){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.in("id",ids);
        setmealService.remove(queryWrapper);
        return R.success("删除成功");
    }

    @PostMapping
    public R<String> setmeal(@RequestBody SetmealDto setmealDto){
        setmealService.savemeal(setmealDto);
        return R.success("添加成功");
    }

    @PostMapping("/status/{status}")
    public R<String> setmealStatus(@PathVariable Integer status,Long...ids){
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.in("id",ids);
        if(status==0){
            updateWrapper.set("status",0);
        }else{
            updateWrapper.set("status",1);
        }
        setmealService.update(updateWrapper);
        return R.success("更新成功");
    }

    @GetMapping("{id}")
    public R<SetmealDto> getSetmealDto(@PathVariable Long id){

        SetmealDto setmealDto= setmealService.getSetmealDto(id);

        return R.success(setmealDto);
    }

    @PutMapping
    public R<String> updateSetmealDto(@RequestBody SetmealDto setmealDto)
    {
        System.out.println(setmealDto);
        setmealService.updateSetmealDto(setmealDto);
        return R.success("ok");
    }

    @GetMapping("/list")
    public R<List<Setmeal>> setmealList(Long categoryId,Integer status){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("category_id",categoryId);
        queryWrapper.eq("status",status);
        List list = setmealService.list(queryWrapper);
        return R.success(list);
    }
}
