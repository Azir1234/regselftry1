package com.azir.controller;

import com.azir.common.R;
import com.azir.entity.Category;
import com.azir.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/page")
    public R<Page> categoryPage(Integer page,Integer pageSize){
        Page<Category> pageInfo=new Page(page,pageSize);
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @DeleteMapping
    public R<String> categoryDelete(Long...ids){
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("id",ids);
        categoryService.remove(queryWrapper);
        return R.success("删除成功");
    }

    @PutMapping
    public R<String> CategoryUpdate(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改成功");
    }

    @PostMapping
    public R<String> CategorySave(@RequestBody Category category){
        categoryService.save(category);
        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R<List<Category>> CategoryList(Integer type){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("type",type);
       List<Category> list= categoryService.list(queryWrapper);
       return R.success(list);
    }
}
