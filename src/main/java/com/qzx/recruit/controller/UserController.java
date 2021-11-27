package com.qzx.recruit.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.qzx.recruit.common.Result;
import com.qzx.recruit.domain.PersonInfo;
import com.qzx.recruit.service.PersonInfoService;
import com.qzx.recruit.util.JwtUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.PipedOutputStream;
import java.security.PublicKey;

/**
 * @author qzx
 * @create 2021-10-27 15:47
 * @function
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    PersonInfoService personInfoService;

    @PostMapping(value = "/register")
    public Result register(@RequestBody PersonInfo personInfo) {
        return personInfoService.insertSelective(personInfo);
    }

    @PostMapping(value = "/login")
    public Result login(@RequestParam("phoneNum") String phoneNum, @RequestParam("password") String password) {
        return personInfoService.selectByPhoneNumAndPassword(phoneNum, password);
    }

    @PostMapping(value = "/userSignUp")
    public Result userSignUp(@RequestBody JSONObject jsonObject) {
        return personInfoService.updateSignUp(jsonObject);
    }

    @PostMapping(value = "/basicInfo")
    public Result basicInfo(HttpServletRequest request) {
        String phoneNum = JwtUtils.verityUser(request.getHeader("token"));
        return personInfoService.selectByPrimaryKey(phoneNum);
    }

    @PostMapping(value = "/getResume")
    public Result getResume(HttpServletRequest request) {
        String phoneNum = JwtUtils.verityUser(request.getHeader("token"));
        return personInfoService.SelectJoinPersonApplyByPhoneNum(phoneNum);
    }

    @PostMapping(value = "/modifyBasic")
    public Result modifyBasic(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String phoneNum = JwtUtils.verityUser(request.getHeader("token"));
        return personInfoService.updateByPrimaryKeySelective(jsonObject, phoneNum);
    }

    @PostMapping(value = "/modifyResume")
    public Result modifyResume(@RequestBody JSONObject jsonObject) {
         return   personInfoService.modifyResume(jsonObject);
    }

    @PostMapping(value = "/getState")
    public Result getState(HttpServletRequest request){
        String phoneNum = JwtUtils.verityUser(request.getHeader("token"));
        return personInfoService.selectStateByPhoneNum(phoneNum);
    }
}

