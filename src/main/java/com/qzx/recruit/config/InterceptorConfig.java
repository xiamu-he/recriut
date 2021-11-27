package com.qzx.recruit.config;

import com.qzx.recruit.interceptor.LeaderLoginInterceptor;
import com.qzx.recruit.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author XiaolongLi
 * Description:
 * @date 2021-07-31 16:45
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/api/user/**")
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/user/register");
        registry.addInterceptor(new LeaderLoginInterceptor())
                .addPathPatterns("/api/leader/**")
                .excludePathPatterns("/api/leader/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept,Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "token")
                .allowedMethods("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
    }
}
