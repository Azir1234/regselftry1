package com.azir.service.impl;

import com.azir.entity.Category;
import com.azir.mapper.CategoryMapper;
import com.azir.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
