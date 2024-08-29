package com.azir.common;

import org.springframework.stereotype.Component;

@Component
public class ThreadLocalParam {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();
    public static void set(Long id){
        threadLocal.set(id);
        return;
    }
    public static Long get(){
        return threadLocal.get();
    }
}
