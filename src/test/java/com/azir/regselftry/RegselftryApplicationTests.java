package com.azir.regselftry;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class RegselftryApplicationTests {
    private long time = 1000*60*60*24;
    // 签名信息
    private String signature = "admin";
    @Test
    void contextLoads() {

        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // 设置头信息Header，头部
                // 设置Token类型
                .setHeaderParam("typ","JWT")
                // 设置算法名称
                .setHeaderParam("alg","HS256")
                // 设置载荷Payload，存放有效信息的地方
                // 用户名
                .claim("username","tom")
                // 用户角色
                .claim("role","admin")
                // 设置主题
                .setSubject("admin-test")
                // 获取有效时间
                .setExpiration(new Date(System.currentTimeMillis()+time))
                // 设置ID，就是JWT的ID
                .setId(UUID.randomUUID().toString())
                // 设置签名signature，对前面两部分的再次加密编码整合
                .signWith(SignatureAlgorithm.HS256,signature)
                // 将三部分进行拼接，形成有效的Token
                .compact();
        System.out.println(jwtToken);
    }


}
