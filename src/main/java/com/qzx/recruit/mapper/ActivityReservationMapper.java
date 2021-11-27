package com.qzx.recruit.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.qzx.recruit.domain.ActivityReservation;

/**
 * @author qzx
 * @create 2021-10-26 0:26
 * @function
 */
public interface ActivityReservationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityReservation record);

    int insertSelective(ActivityReservation record);

    ActivityReservation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityReservation record);

    int updateByPrimaryKey(ActivityReservation record);

    List<ActivityReservation> selectAllByDayGreaterThanEqualCurDay(@Param("today")String today);

    List<ActivityReservation> selectByDepartment(@Param("department")Integer department);


}