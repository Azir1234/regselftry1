package com.azir.utils;

import io.jsonwebtoken.*;
import org.junit.Test;



import java.util.Date;
import java.util.UUID;

public class jwtUtil {
    private static long time = 1000*60*60*24;
    // 签名信息
    private static String signature = "admin";

    public static String jwt(String id){

        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // 设置头信息Header，头部
                // 设置Token类型
                .setHeaderParam("typ","JWT")
                // 设置算法名称
                .setHeaderParam("alg","HS256")
                // 设置载荷Payload，存放有效信息的地方
                // 用户名
                .claim("id",id)
                // 用户角色
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
        return jwtToken;
    }
    public static String  jwtParse(String jwtToken){
        JwtParser jwtParser= Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(jwtToken);
        Claims body = claimsJws.getBody();
        return (String) body.get("id");
    }


}
