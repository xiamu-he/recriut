package com.qzx.recruit.mapper;

import com.qzx.recruit.domain.CollegeDistribution;
import com.qzx.recruit.domain.DepartmentSexInfo;
import com.qzx.recruit.domain.PersonInfo;
import com.qzx.recruit.domain.PersonInfoWithPersonApply;
import com.qzx.recruit.vo.FilterResumeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qzx
 * @create 2021-10-26 0:26
 * @function
 */
public interface PersonInfoMapper {
    int deleteByPrimaryKey(String phoneNum);

    int insert(PersonInfo record);

    int insertSelective(PersonInfo record);

    PersonInfo selectByPrimaryKey(String phoneNum);

    PersonInfo selectByPhoneNumAndAlterDepart(@Param("phoneNum") String phoneNum, @Param("alterDepart") Integer alterDepart);

    int updateByPrimaryKeySelective(PersonInfo record);

    int updateByPrimaryKey(PersonInfo record);

    PersonInfoWithPersonApply SelectJoinPersonApply(@Param("phoneNum") String phoneNum, @Param("position") Integer position);

    List<PersonInfoWithPersonApply> SelectJoinPersonApplyByPersonInfo(@Param("filterResumeVo") FilterResumeVo filterResumeVo);

    List<PersonInfo> selectByPhoneNums(@Param("phoneNums") List<String> phoneNums);

    PersonInfo selectByPhoneNum(@Param("phoneNum")String phoneNum);

    List<CollegeDistribution> selectCollegeAllByGrade(@Param("grade") Integer grade);

    List<DepartmentSexInfo> selectSexByGrade(@Param("grade")Integer grade);

    PersonInfo selectByPhoneNumAndPassword(@Param("phoneNum")String phoneNum,@Param("password")String password);

    PersonInfoWithPersonApply SelectJoinPersonApplyByPhoneNum(@Param("phoneNum") String phoneNum);

    Integer selectStateByPhoneNum(@Param("phoneNum")String phoneNum);


}