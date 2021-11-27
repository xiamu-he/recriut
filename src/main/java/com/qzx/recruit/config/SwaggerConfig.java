package com.qzx.recruit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author qzx
 * @create 2021-10-30 9:28
 * @function
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment) {
        Profiles profiles = Profiles.of("dev", "test");
        //environment。acceptsProfiles判断自己是否在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).enable(flag);
    }

    // 配置swagger基本信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("HUAWEI", "http://localhost:9655/", "email@.com");
        return new ApiInfo(
                "华俱迎新后台的Swagger文档",
                "华俱迎新后台的API文档，可以直接在当前网站测试",
                "v1.0",
                "http://localhost:9655/",
                contact,
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
