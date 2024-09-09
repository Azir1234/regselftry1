package com.azir.controller;

import com.azir.common.R;
import com.azir.entity.ShoppingCart;
import com.azir.service.ShoppingCartService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
@Transactional
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/list")
    public R<List<ShoppingCart>> listShoppingCart(HttpSession session){
        Long userId= (Long) session.getAttribute("user");
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
        return R.success(list);
    }

    @PostMapping("/add")
    public R<String> addShoppingCart(@RequestBody ShoppingCart shoppingCart, HttpSession session){
        System.out.println(shoppingCart);
        Long userId= (Long) session.getAttribute("user");
        shoppingCart.setUserId(userId);
        shoppingCartService.saveOrUpdate(shoppingCart);
        return  R.success("成功");
    }
    @DeleteMapping("/clean")
    public R<String> cleanShoppingCart(HttpSession session){
        Long userId = (Long) session.getAttribute("user");
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        shoppingCartService.remove(queryWrapper);
        return R.success("清空成功");
    }

    @PostMapping("/sub")
    public R<String> subShoppingCart(@RequestBody ShoppingCart shoppingCart){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("setmeal_id",shoppingCart.getSetmealId());
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
        ShoppingCart shoppingCart1=list.get(0);
        shoppingCartService.removeById(list.get(0).getId());
        return R.success("成功");
    }
}
