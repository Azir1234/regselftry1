package com.azir.Filter;


import com.alibaba.fastjson.JSON;
import com.azir.common.R;
import com.azir.common.ThreadLocalParam;
import com.azir.entity.Employee;
import com.azir.utils.jwtUtil;
import org.springframework.util.AntPathMatcher;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = "/*",filterName = "logFilter2")
public class logFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();// /backend/index.html

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //2、判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //3、如果不需要处理，则直接放行
        if(check){
            filterChain.doFilter(request,response);
            return;
        }

        String ids  = (String) request.getSession().getAttribute("employeeId");
        System.out.println(ids);
        //4-1、判断登录状态，如果已登录，则直接放行
        if( ids!= null){
            Long employeeId=  Long.valueOf( jwtUtil.jwtParse(ids));
            ThreadLocalParam.set(employeeId);
            filterChain.doFilter(request,response);
            return;
        }

        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }
    public Boolean check(String []urls,String url){
        for (String s : urls) {

            Boolean b=PATH_MATCHER.match(s,url);
           if(b){
               return true;
           }
        }
        return false;
    }
}
