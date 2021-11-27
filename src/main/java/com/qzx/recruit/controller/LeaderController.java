package com.qzx.recruit.controller;

import com.alibaba.fastjson.JSONObject;
import com.qzx.recruit.common.Result;
import com.qzx.recruit.domain.ActivityReservation;
import com.qzx.recruit.domain.Leader;
import com.qzx.recruit.service.ActivityReservationService;
import com.qzx.recruit.service.ImageService;
import com.qzx.recruit.service.LeaderService;
import com.qzx.recruit.service.PersonInfoService;
import com.qzx.recruit.util.JwtUtils;
import com.qzx.recruit.vo.FilterResumeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @author qzx
 * @create 2021-10-25 19:22
 * @function
 */

@Api(tags = "接口")
@RestController
@RequestMapping(value = "/api/leader")
public class LeaderController {
    @Autowired
    LeaderService leaderService;

    @Autowired
    PersonInfoService personInfoService;

    @Autowired
    ActivityReservationService activityReservationService;

    @Autowired
    ImageService imageService;

    @ApiOperation(value = "登录",notes = "账号密码")
    @PostMapping(value = "/login")
    public Result login(@RequestBody Leader leader){
        return leaderService.selectByPhoneNumAndPassword(leader.getPhoneNum(),leader.getPassword());
    }

    @ApiOperation(value = "查看简历详情",notes = "需要token")
    @GetMapping(value = "/getResumeDetail")
    public Result getResumeDetail(@RequestParam("phoneNum") String phoneNum, HttpServletRequest request){
        Integer position = JwtUtils.verifyRoleId(request.getHeader("token"));
        return personInfoService.SelectJoinPersonApply(phoneNum,position);
    }

    @ApiOperation(value = "修改简历状态",notes = "需要token")
    @PostMapping(value = "/updateResume")
    public Result updateResume(@RequestBody JSONObject jsonObject, HttpServletRequest request){
        Integer position = JwtUtils.verifyRoleId(request.getHeader("token"));
        return personInfoService.updateByPrimaryKeySelective(jsonObject,position);
    }

    @ApiOperation(value = "筛选简历",notes = "需要token")
    @PostMapping(value = "/filterResume")
    public Result filterResume(@Validated @RequestBody FilterResumeVo filterResumeVo,HttpServletRequest request){
        Integer position = JwtUtils.verifyRoleId(request.getHeader("token"));
        if (position != 8)
            filterResumeVo.setAlterDepart(Collections.singletonList(position));
        return personInfoService.SelectJoinPersonApplyByPersonInfo(filterResumeVo);
    }

    @ApiOperation(value = "下发面试通知",notes = "header 需要token，参数 : phoneNums：电话号码数组，time：选择的面试时间，template ：短信模板（包含地址） ")
    @PostMapping(value = "/noticeMessage")
    public Result noticeMessage(@RequestBody JSONObject jsonObject,HttpServletRequest request){
        Integer position = JwtUtils.verifyRoleId(request.getHeader("token"));
        return personInfoService.noticeMessage(jsonObject,position);
    }

    @ApiOperation(value = "获取学院分布",notes = "需要token，参数：grade 年级")
    @PostMapping(value = "/getCollegeDistribution")
    public Result getCollegeDistribution(@RequestParam("grade")Integer grade){
        return personInfoService.selectByGrade(grade);
    }

    @ApiOperation(value = "录取部门男女分布",notes = "需要token，参数：grade 年级")
    @PostMapping(value = "/getDepartmentSex")
    public Result getDepartmentSex(@RequestParam("grade")Integer grade){
        return personInfoService.selectSexByGrade(grade);
    }

    @ApiOperation(value = "预约活动室",notes = "需要token，日期设置如：2021-08-22 格式 yyyy-MM-dd")
    @PostMapping(value = "/addActivityReservation")
    public Result addActivityReservation(@RequestBody ActivityReservation activityReservation,HttpServletRequest request){
        Integer position = JwtUtils.verifyRoleId(request.getHeader("token"));
        return activityReservationService.insert(activityReservation,position);
    }

    @ApiOperation(value = "获取预约活动室可用时间",notes = "需要token")
    @PostMapping(value = "/getAvailableActivityReservationTime")
    public Result getAvailableActivityReservationTime(){
        return activityReservationService.selectAllByDayGreaterThanEqualCurDay();
    }

    @ApiOperation(value = "改变活动室预约状态",notes = "需要token，0 未使用，1 取消活动，2 提前结束")
    @PostMapping(value = "/changeActivityState")
    public Result changeActivityState(@RequestBody ActivityReservation activityReservation){
        return activityReservationService.updateByPrimaryKeySelective(activityReservation);
    }

    @ApiOperation(value = "我的预约列表",notes = "需要token")
    @PostMapping(value = "/myActivityReservation")
    public Result myActivityReservation(@RequestParam("page")Integer page,@RequestParam("pageSize")Integer pageSize, HttpServletRequest request){
        Integer position = JwtUtils.verifyRoleId(request.getHeader("token"));
        return activityReservationService.selectByDepartment(position,page,pageSize);
    }

    @ApiOperation(value = "预约详情",notes = "需要token，参数：主键id")
    @PostMapping(value = "/activityReservationDetail")
    public Result activityReservationDetail(@RequestParam("id")Integer id){
        return activityReservationService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "上传图片",notes = "需要token，参数：file 上传的文件")
    @PostMapping(value = "/updateImage")
    public Result updateImage(@RequestParam("file")MultipartFile file){
        return imageService.insertSelective(file);
    }
}
