package com.qzx.recruit.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qzx
 * @create 2021-10-26 0:26
 * @function
 */
@ApiModel(value = "activity_reservation")
@Data
public class ActivityReservation {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 预约部门
     */
    @ApiModelProperty(value = "预约部门")
    private Integer department;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phoneNum;

    /**
     * 人数
     */
    @ApiModelProperty(value = "人数")
    private Integer personNum;

    /**
     * 活动主题
     */
    @ApiModelProperty(value = "活动主题")
    private String activityTheme;

    /**
     * 活动内容
     */
    @ApiModelProperty(value = "活动内容")
    private String activityContent;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Integer startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Integer endTime;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private String day;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer state;
}