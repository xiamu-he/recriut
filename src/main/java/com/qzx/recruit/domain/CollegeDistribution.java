package com.qzx.recruit.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qzx
 * @create 2021-10-26 17:45
 * @function
 */

@Data
public class CollegeDistribution {
    @ApiModelProperty(value = "数量")
    private Integer num;

    @ApiModelProperty(value = "学院代码")
    private Integer college;

    @ApiModelProperty(value = "年级")
    private Integer grade;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "状态")
    private Integer state;
}
