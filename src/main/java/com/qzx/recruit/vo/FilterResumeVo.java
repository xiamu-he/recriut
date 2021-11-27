package com.qzx.recruit.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author qzx
 * @create 2021-10-26 15:08
 * @function
 */
@Data
public class FilterResumeVo {

    @NotNull(message = "page 不能为空")
    @ApiModelProperty(value = "页码")
    private Integer page;

    @NotNull(message = "pageSize 不能为空")
    @ApiModelProperty(value = "每页显示数量")
    private Integer pageSize;

    @ApiModelProperty(value = "关键词")
    private String keyWord;

    @ApiModelProperty(value = "华俱第*届")
    private Integer grade;

    @ApiModelProperty(value = "性别")
    private List<Integer> sex;

    @ApiModelProperty(value = "学院")
    private List<Integer> college;

    @ApiModelProperty(value = "校区")
    private List<String> campus;

    @ApiModelProperty(value = "第一志愿部门")
    private List<Integer> department;

    @ApiModelProperty(value = "第二志愿部门")
    private List<Integer> secondDepart;

    @ApiModelProperty(value = "所属部门")
    private List<Integer> alterDepart;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "专业")
    private List<String> profession;

    @ApiModelProperty(value = "字段排序")
    private List<SortedVo> sorter;
}
