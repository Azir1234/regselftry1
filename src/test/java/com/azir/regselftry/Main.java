package com.azir.regselftry;

import com.azir.utils.jwtUtil;

public class Main {
    public static void main(String[] args) {
        Long id=1313758345234L;
        String ids=id.toString();


        String s= jwtUtil.jwt(ids);
        System.out.println(s);
        System.out.println( Long.valueOf( jwtUtil.jwtParse(s)));


    }
}
