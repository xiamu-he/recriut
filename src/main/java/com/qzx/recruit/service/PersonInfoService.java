package com.qzx.recruit.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.gson.JsonObject;
import com.qzx.recruit.common.Result;
import com.qzx.recruit.domain.*;
import com.qzx.recruit.handler.UserDefinedException;
import com.qzx.recruit.mapper.PersonApplyMapper;
import com.qzx.recruit.util.JwtUtils;
import com.qzx.recruit.vo.BasicInfoVo;
import com.qzx.recruit.vo.FilterResumeVo;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.qzx.recruit.mapper.PersonInfoMapper;
import org.springframework.util.DigestUtils;

import java.util.*;

/**
 * @author qzx
 * @create 2021-10-25 18:04
 * @function
 */
@Service
public class PersonInfoService {

    @Resource
    private PersonInfoMapper personInfoMapper;

    @Resource
    private PersonApplyMapper personApplyMapper;


    public int deleteByPrimaryKey(String phoneNum) {
        return personInfoMapper.deleteByPrimaryKey(phoneNum);
    }


    public int insert(PersonInfo record) {
        return personInfoMapper.insert(record);
    }


    public Result insertSelective(PersonInfo record) {
        String phoneNum = record.getPhoneNum();

        if (personInfoMapper.selectByPhoneNum(phoneNum) != null) {
            throw new UserDefinedException("该手机号已存在");
        }
        String password = DigestUtils.md5DigestAsHex((record.getPhoneNum() + record.getPassword()).getBytes());
        record.setPassword(password);
        //第*届华俱成员
        record.setGrade(DateUtil.year(new Date()) - 2010);
        //简历状态，1：未申请
        record.setState(1);
        //是否申请，0：未报名
        record.setIsapply(0);
        //设置剩余可修改简历次数
        record.setAltertimes(3);

        if ((personInfoMapper.insertSelective(record) > 0)) {
            return Result.success("注册成功");
        }

        return Result.error("注册失败，请重试!");
    }


    public Result selectByPrimaryKey(String phoneNum) {
        PersonInfo personInfo = personInfoMapper.selectByPrimaryKey(phoneNum);
        if(personInfo == null){
            throw new UserDefinedException("数据库中无此号码");
        }

        BasicInfoVo basicInfoVo=new BasicInfoVo();
        basicInfoVo.setPhoneNum(personInfo.getPhoneNum());
        basicInfoVo.setName(personInfo.getName());
        basicInfoVo.setSex(personInfo.getSex());
        basicInfoVo.setDepartment(personInfo.getDepartment());
        basicInfoVo.setCollege(personInfo.getCollege());
        basicInfoVo.setProfession(personInfo.getProfession());
        basicInfoVo.setCampus(personInfo.getCampus());
        basicInfoVo.setStuId(personInfo.getStuId());

        return Result.success(basicInfoVo);
    }


    public Result updateByPrimaryKeySelective(JSONObject jsonObject, Integer position) {
        PersonInfo personInfo = JSONObject.toJavaObject(jsonObject, PersonInfo.class);
        PersonApply personApply = JSONObject.toJavaObject(jsonObject, PersonApply.class);

        if (position == 8 || personInfoMapper.selectByPhoneNumAndAlterDepart(personInfo.getPhoneNum(), position) != null) {
            personInfoMapper.updateByPrimaryKeySelective(personInfo);
            if (personApply.getRemark() != null) {
                personApplyMapper.updateByPrimaryKeySelective(personApply);
            }
        } else {
            throw new UserDefinedException("该账号不存在或您没有权限");
        }
        return Result.success("改变简历状态成功");
    }


    public int updateByPrimaryKey(PersonInfo record) {
        return personInfoMapper.updateByPrimaryKey(record);
    }

    public Result SelectJoinPersonApply(String phoneNum, Integer position) {

        PersonInfoWithPersonApply personInfoWithPersonApply = personInfoMapper.SelectJoinPersonApply(phoneNum, position);
        if (personInfoWithPersonApply == null) {
            return Result.error("无此数据或无权限");
        }
        return Result.success(personInfoWithPersonApply);
    }


    public PersonInfo selectByPhoneNumAndAlterDepart(String phoneNum, Integer alterDepart) {
        return personInfoMapper.selectByPhoneNumAndAlterDepart(phoneNum, alterDepart);
    }

    public Result SelectJoinPersonApplyByPersonInfo(FilterResumeVo filterResumeVo) {
        PageHelper.startPage(filterResumeVo.getPage(), filterResumeVo.getPageSize());
        List<PersonInfoWithPersonApply> personInfoWithPersonApplies = personInfoMapper.SelectJoinPersonApplyByPersonInfo(filterResumeVo);

        return Result.success(personInfoWithPersonApplies);
    }

    public Result noticeMessage(JSONObject jsonObject, Integer position) {
        JSONArray jsonArray = jsonObject.getJSONArray("phoneNums");

        String time = jsonObject.getString("time");
        String template = jsonObject.getString("template");

        List<String> phoneNums = new LinkedList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            phoneNums.add(jsonArray.getString(i));
        }
        template = template.replaceAll("<time>", time);
        List<PersonInfo> personInfos = personInfoMapper.selectByPhoneNums(phoneNums);
        for (PersonInfo personInfo : personInfos) {
            personInfo.setNotice(template);
            personInfo.setInterviewtime(time);
            if (position == 8 || personInfo.getAlterDepart() == position) {
                if (!(personInfoMapper.updateByPrimaryKey(personInfo) > 0)) {
                    throw new UserDefinedException("更新数据库失败");
                }
            }
        }
        return Result.success("成功通知了" + personInfos.size() + "人");
    }

    public Result selectByGrade(Integer grade) {
        return Result.success(personInfoMapper.selectCollegeAllByGrade(grade));
    }

    public Result selectSexByGrade(Integer grade) {
        return Result.success(personInfoMapper.selectSexByGrade(grade));
    }

    public Result selectByPhoneNumAndPassword(String phoneNum, String password) {
        if (personInfoMapper.selectByPhoneNum(phoneNum) == null) {
            throw new UserDefinedException("该手机号未注册");
        }
        String passwordMDK5 = DigestUtils.md5DigestAsHex((phoneNum + password).getBytes());
        PersonInfo personInfo = personInfoMapper.selectByPhoneNumAndPassword(phoneNum, passwordMDK5);
        if (personInfo == null) {
            throw new UserDefinedException("手机号或密码错误");
        }
        String token = JwtUtils.getToken(personInfo.getPhoneNum());
        Map<String, Object> map = new HashMap<>();
        map.put("user", personInfo);
        map.put("token", token);
        return Result.success(map);
    }

    public Result updateSignUp(JSONObject jsonObject) {
        PersonInfo personInfo = JSONObject.toJavaObject(jsonObject, PersonInfo.class);
        PersonApply personApply = JSONObject.toJavaObject(jsonObject, PersonApply.class);
        //设置申请状态 1：已申请
        personInfo.setIsapply(1);
        //设置简历状态 2：未查看
        personInfo.setState(2);
        //设置申请时间
        personInfo.setApplytime(new Date());
        //设置简历可修改次数
        personInfo.setAltertimes(3);
        //设置所属部门
        personInfo.setAlterDepart(personInfo.getDepartment());

        personApply.setPhoneNum(personInfo.getPhoneNum());
        if(personApplyMapper.insertSelective(personApply)>0&&personInfoMapper.updateByPrimaryKeySelective(personInfo)>0){
            return Result.success("报名成功");
        }
        return Result.error("报名失败");
    }

    public Result SelectJoinPersonApplyByPhoneNum(String phoneNum) {
        return Result.success(personInfoMapper.SelectJoinPersonApplyByPhoneNum(phoneNum));
    }

    public Result updateByPrimaryKeySelective(JSONObject jsonObject, String phoneNum) {
        PersonInfo personInfo = JSONObject.toJavaObject(jsonObject, PersonInfo.class);
        if(personInfoMapper.updateByPrimaryKeySelective(personInfo)>0){
            return Result.success("修改成功");
        }
        return Result.error("修改失败请重试");
    }

    public Result modifyResume(JSONObject jsonObject){
        PersonInfo personInfo = JSONObject.toJavaObject(jsonObject, PersonInfo.class);
        PersonApply personApply = JSONObject.toJavaObject(jsonObject, PersonApply.class);
        PersonInfo personInfo1 = personInfoMapper.selectByPrimaryKey(personInfo.getPhoneNum());
        if (personInfo1.getAltertimes() == 0) {
            return Result.error("已修改三次");
        }

        personInfo.setAltertimes(personInfo1.getAltertimes()-1);
        personInfo.setAlterDepart(personInfo.getDepartment());
        personInfo.setApplytime(new Date());

        if(personApplyMapper.updateByPrimaryKeySelective(personApply)>0&&personInfoMapper.updateByPrimaryKeySelective(personInfo)>0){
            return Result.success("修改成功");
        }
        return Result.error("修改失败，请重试");
    }

    public Result selectStateByPhoneNum(String phoneNum) {
        return Result.success(personInfoMapper.selectStateByPhoneNum(phoneNum));
    }
}






