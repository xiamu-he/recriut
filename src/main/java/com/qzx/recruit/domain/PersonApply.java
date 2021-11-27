package com.qzx.recruit.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qzx
 * @create 2021-10-26 0:26
 * @function
 */
@ApiModel(value = "person_apply")
@Data
public class PersonApply {
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phoneNum;

    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍")
    private String introduction;

    /**
     * 特长
     */
    @ApiModelProperty(value = "特长")
    private String strength;

    /**
     * 目标方向
     */
    @ApiModelProperty(value = "目标方向")
    private String direction;

    /**
     * 获得荣誉
     */
    @ApiModelProperty(value = "获得荣誉")
    private String honor;

    /**
     * 实践经历
     */
    @ApiModelProperty(value = "实践经历")
    private String practice;

    /**
     * 兴趣爱好
     */
    @ApiModelProperty(value = "兴趣爱好")
    private String interest;

    /**
     * 加入目的
     */
    @ApiModelProperty(value = "加入目的")
    private String propose;

    /**
     * 简历图片地址
     */
    @ApiModelProperty(value = "简历图片地址")
    private String resume;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remark;
}