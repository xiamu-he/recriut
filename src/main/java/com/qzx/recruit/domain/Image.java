package com.qzx.recruit.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qzx
 * @create 2021-10-26 0:26
 * @function
 */
@ApiModel(value = "image")
@Data
public class Image {
    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "")
    private String url;
}