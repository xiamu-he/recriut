package com.qzx.recruit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qzx
 * @create 2021-10-25 18:11
 * @function
 */
@RestController
public class TestController {
    @RequestMapping(value = "/test")
    public String test(){
        return "123";
    }
}
