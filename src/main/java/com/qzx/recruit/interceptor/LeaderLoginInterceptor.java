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
public class LeaderLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = request.getHeader("token");
        if (token == null) {
            throw new UnauthorizedExecption("未登录");
        }

        Integer position = JwtUtils.verifyRoleId(token);
        if (position == null) {
            throw new UnauthorizedExecption("token失效");
        }
        return true;
    }
}
