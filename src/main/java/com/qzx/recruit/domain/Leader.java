package com.qzx.recruit.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qzx
 * @create 2021-10-26 0:26
 * @function
 */
@ApiModel(value = "leader")
@Data
public class Leader {
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phoneNum;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 职位代码
     */
    @ApiModelProperty(value = "职位代码")
    private Integer position;
}