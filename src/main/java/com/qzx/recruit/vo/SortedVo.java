package com.qzx.recruit.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qzx
 * @create 2021-10-26 15:23
 * @function
 */
@Data
public class SortedVo {

    @ApiModelProperty(value = "排序字段")
    private String prop;

    @ApiModelProperty(value = "升序 asc，降序 desc")
    private String order;
}
