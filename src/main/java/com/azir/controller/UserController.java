package com.azir.controller;

import com.azir.common.R;
import com.azir.entity.User;
import com.azir.service.ShoppingCartService;
import com.azir.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<String> login(@RequestBody User user, HttpSession session){
        QueryWrapper<User> queryWrapper=new QueryWrapper();
        queryWrapper.eq("phone",user.getPhone());
        User user1= userService.getOne(queryWrapper);
        session.setAttribute("user",user1.getId());
         if(user1!=null){
             return R.success("成功登录");
         }else{

             return R.error("登录失败");
         }


    }

    @PostMapping("/loginout")
    public R<String> loginOut(HttpSession session){

        session.removeAttribute("user");

        return R.success("退出成功");
    }

}
