package com.azir.service;

import com.azir.entity.Setmeal;
import com.azir.entity.SetmealDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SetmealService extends IService<Setmeal> {
    void savemeal(SetmealDto setmealDto);

    SetmealDto getSetmealDto(Long id);

    SetmealDto updateSetmealDto(SetmealDto setmealDto);
}
