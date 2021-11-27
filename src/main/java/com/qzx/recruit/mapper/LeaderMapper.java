package com.qzx.recruit.mapper;

import com.qzx.recruit.domain.Leader;import org.apache.ibatis.annotations.Param;

/**
 * @author qzx
 * @create 2021-10-26 0:26
 * @function
 */
public interface LeaderMapper {
    int deleteByPrimaryKey(String phoneNum);

    int insert(Leader record);

    int insertSelective(Leader record);

    Leader selectByPrimaryKey(String phoneNum);

    int updateByPrimaryKeySelective(Leader record);

    int updateByPrimaryKey(Leader record);

    Leader selectByPhoneNumAndPassword(@Param("phoneNum") String phoneNum, @Param("password") String password);
}