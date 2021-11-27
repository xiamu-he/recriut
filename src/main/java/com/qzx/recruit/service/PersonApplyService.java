package com.qzx.recruit.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.qzx.recruit.domain.PersonApply;
import com.qzx.recruit.mapper.PersonApplyMapper;

/**
 * @author qzx
 * @create 2021-10-25 18:04
 * @function
 */
@Service
public class PersonApplyService {

    @Resource
    private PersonApplyMapper personApplyMapper;


    public int deleteByPrimaryKey(String phoneNum) {
        return personApplyMapper.deleteByPrimaryKey(phoneNum);
    }


    public int insert(PersonApply record) {
        return personApplyMapper.insert(record);
    }


    public int insertSelective(PersonApply record) {
        return personApplyMapper.insertSelective(record);
    }


    public PersonApply selectByPrimaryKey(String phoneNum) {
        return personApplyMapper.selectByPrimaryKey(phoneNum);
    }


    public int updateByPrimaryKeySelective(PersonApply record) {
        return personApplyMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(PersonApply record) {
        return personApplyMapper.updateByPrimaryKey(record);
    }

}






