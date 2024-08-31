package com.azir.controller;

import com.azir.common.R;
import com.azir.entity.AddressBook;
import com.azir.service.AddressBookService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/addressBook")
public class AddressBookController{
    @Autowired
    private AddressBookService addressBookService;

    @PutMapping("/default")
    public R<String> defaultAddress(@RequestBody AddressBook addressBook,HttpSession session){
        Long userId= (Long) session.getAttribute("user");
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.set("is_default",0);
        updateWrapper.eq("user_id",userId);
        addressBookService.update(updateWrapper);
        UpdateWrapper updateWrapper1=new UpdateWrapper();
        updateWrapper1.set("is_default",1);
        updateWrapper1.eq("id",addressBook.getId());
        addressBookService.update(updateWrapper1);
        return R.success("设置成功");

    }


    @PutMapping
    public R<String> updateAddress(@RequestBody AddressBook addressBook){
        addressBookService.updateById(addressBook);
        return R.success("更新成功");
    }
    @GetMapping("/list")
    public R<List<AddressBook>> listAddress(HttpSession session){
        Long userId = (Long) session.getAttribute("user");
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        List list=addressBookService.list(queryWrapper);
        return R.success(list);
    }
    @GetMapping("{id}")
    public R<AddressBook> idAddress(@PathVariable Long id){
        AddressBook addressBook= addressBookService.getById(id);
        return R.success(addressBook);
    }
    @GetMapping("/default")
    public R<AddressBook> getDeAddress(HttpSession session){
       Long userId= (Long) session.getAttribute("user");
       QueryWrapper queryWrapper=new QueryWrapper();
       queryWrapper.eq("user_id",userId);
       queryWrapper.eq("is_default",1);
       AddressBook addressBook= addressBookService.getOne(queryWrapper);
       return R.success(addressBook);
    }

}
