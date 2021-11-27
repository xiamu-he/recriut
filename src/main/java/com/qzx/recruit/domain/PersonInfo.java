package com.qzx.recruit.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * @author qzx
 * @create 2021-10-26 0:26
 * @function
 */
@ApiModel(value = "person_info")
@Data
public class PersonInfo {
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phoneNum;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 学院
     */
    @ApiModelProperty(value = "学院")
    private Integer college;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业")
    private String profession;

    /**
     * 学号
     */
    @ApiModelProperty(value = "学号")
    private String stuId;

    /**
     * 校区
     */
    @ApiModelProperty(value = "校区")
    private String campus;

    /**
     * 华俱第X届
     */
    @ApiModelProperty(value = "华俱第X届")
    private Integer grade;

    /**
     * 所选部门
     */
    @ApiModelProperty(value = "所选部门")
    private Integer department;

    /**
     * 第二部门
     */
    @ApiModelProperty(value = "第二部门")
    private Integer secondDepart;

    /**
     * 调剂部门
     */
    @ApiModelProperty(value = "调剂部门")
    private Integer alterDepart;

    /**
     * 是否调剂
     */
    @ApiModelProperty(value = "是否调剂")
    private Integer adjust;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer state;

    /**
     * 简历得分
     */
    @ApiModelProperty(value = "简历得分")
    private Integer resumeScore;

    /**
     * 是否录取
     */
    @ApiModelProperty(value = "是否录取")
    private Integer isapply;

    /**
     * 录取时间
     */
    @ApiModelProperty(value = "录取时间")
    private Date applytime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Integer altertimes;

    /**
     * 面试时间
     */
    @ApiModelProperty(value = "面试时间")
    private String interviewtime;

    /**
     * 面试得分
     */
    @ApiModelProperty(value = "面试得分")
    private Integer viewScore;

    /**
     * 短信通知
     */
    @ApiModelProperty(value = "短信通知")
    private String notice;
}