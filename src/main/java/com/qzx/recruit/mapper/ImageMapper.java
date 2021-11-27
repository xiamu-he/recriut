package com.qzx.recruit.mapper;

import com.qzx.recruit.domain.Image;

/**
 * @author qzx
 * @create 2021-10-26 0:26
 * @function
 */
public interface ImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}