package com.qzx.recruit.mapper;

import com.qzx.recruit.domain.PersonApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qzx
 * @create 2021-10-26 0:26
 * @function
 */
public interface PersonApplyMapper {
    int deleteByPrimaryKey(String phoneNum);

    int insert(PersonApply record);

    int insertSelective(PersonApply record);

    PersonApply selectByPrimaryKey(String phoneNum);

    int updateByPrimaryKeySelective(PersonApply record);

    int updateByPrimaryKey(PersonApply record);

}