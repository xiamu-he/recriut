package com.qzx.recruit.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.qzx.recruit.common.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.qzx.recruit.mapper.ActivityReservationMapper;
import com.qzx.recruit.domain.ActivityReservation;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

/**
 * @author qzx
 * @create 2021-10-25 18:04
 * @function
 */
@Service
public class ActivityReservationService {

    @Resource
    private ActivityReservationMapper activityReservationMapper;

    public int deleteByPrimaryKey(Integer id) {
        return activityReservationMapper.deleteByPrimaryKey(id);
    }

    public Result insert(ActivityReservation record, Integer position) {

        String today = DateUtil.today();
        if (record.getDay().compareTo(today) < 0) return Result.error("不能预约过去的时间");

        Date dateTime = DateUtil.beginOfDay(new Date());
        dateTime = DateUtil.offset(dateTime, DateField.DAY_OF_MONTH, 6);


        if (record.getDay().compareTo(DateUtil.formatDate(dateTime)) > 0) return Result.error("只能预约未来7天的时间");

        record.setState(0);
        record.setDepartment(position);
        if (activityReservationMapper.insert(record) > 0) {
            return Result.success("预约成功");
        }
        return Result.error("预约失败");
    }


    public int insertSelective(ActivityReservation record) {
        return activityReservationMapper.insertSelective(record);
    }


    public Result selectByPrimaryKey(Integer id) {
        return Result.success(activityReservationMapper.selectByPrimaryKey(id));
    }


    public Result updateByPrimaryKeySelective(ActivityReservation record) {
        if(activityReservationMapper.updateByPrimaryKeySelective(record)>0){
            return Result.success("活动室预约修改成功");
        }

        return Result.error("活动室预约状态修改失败");
    }


    public int updateByPrimaryKey(ActivityReservation record) {
        return activityReservationMapper.updateByPrimaryKey(record);
    }

    public Result selectAllByDayGreaterThanEqualCurDay() {
        //获取从当天开始的可用时间
        Map<String, HashSet<Integer>> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();

        String today = DateUtil.today();
        //排除今天已经过去的时间
        String now = DateUtil.now();
        DateTime dateTime = DateUtil.beginOfDay(new Date());
        dateTime = DateUtil.offset(dateTime, DateField.HOUR, 8);
        dateTime = DateUtil.offset(dateTime, DateField.MINUTE, 30);
        for (int i = 0; now.compareTo(DateUtil.formatDateTime(dateTime)) > 0; i++) {
            set.add(i);
            dateTime = DateUtil.offset(dateTime, DateField.MINUTE, 30);
        }
        map.put(today, set);

        //排除已经被预约的时间
        List<ActivityReservation> activityReservations = activityReservationMapper.selectAllByDayGreaterThanEqualCurDay(today);
        for (ActivityReservation activityReservation : activityReservations) {
            String day = activityReservation.getDay();
            set=new HashSet<>();
            for (int i = activityReservation.getStartTime(); i < activityReservation.getEndTime(); i++) {
                set.add(i);
            }
            if (map.containsKey(day)) {
                HashSet<Integer> integers = map.get(day);
                integers.addAll(set);
                map.put(day,set);
            }else {
                map.put(day,set);
            }
        }
        return Result.success(map);
    }

    public Result selectByDepartment(Integer department,Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<ActivityReservation> activityReservations = activityReservationMapper.selectByDepartment(department);
        return Result.success(activityReservations);
    }
}






