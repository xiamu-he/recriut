package com.qzx.recruit.interceptor;

import com.qzx.recruit.handler.UnauthorizedExecption;
import com.qzx.recruit.util.JwtUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qzx
 * @create 2021-10-25 20:14
 * @function
 */
public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = request.getHeader("token");
        //未登录
        if (token == null) {
            throw new UnauthorizedExecption("header 缺少token");
        }

        //登录失效
        String phoneNum = JwtUtils.verityUser(token);
        if (phoneNum == null) {
            throw new UnauthorizedExecption("token失效");
        }

        return true;
    }
}
