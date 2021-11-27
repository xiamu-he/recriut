package com.qzx.recruit.service;

import com.qzx.recruit.common.Result;
import com.qzx.recruit.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.qzx.recruit.domain.Leader;
import com.qzx.recruit.mapper.LeaderMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qzx
 * @create 2021-10-25 18:04
 * @function
 */
@Service
public class LeaderService {

    @Resource
    private LeaderMapper leaderMapper;


    public int deleteByPrimaryKey(String phoneNum) {
        return leaderMapper.deleteByPrimaryKey(phoneNum);
    }


    public int insert(Leader record) {
        return leaderMapper.insert(record);
    }


    public int insertSelective(Leader record) {
        return leaderMapper.insertSelective(record);
    }


    public Leader selectByPrimaryKey(String phoneNum) {
        return leaderMapper.selectByPrimaryKey(phoneNum);
    }


    public int updateByPrimaryKeySelective(Leader record) {
        return leaderMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Leader record) {
        return leaderMapper.updateByPrimaryKey(record);
    }

    public Result selectByPhoneNumAndPassword(String phoneNum, String password) {
        Leader leader = new Leader();
        leader = leaderMapper.selectByPrimaryKey(phoneNum);
        if (leader == null) {
            return Result.error("账号未注册");
        }
        leader = leaderMapper.selectByPhoneNumAndPassword(phoneNum, password);
        if (leader == null) {
            return Result.error("账号或密码不正确");
        }
        String token = JwtUtils.getToken(leader.getPosition());
        Map<String, Object> map = new HashMap<>();
        map.put("user", leader);
        map.put("token", token);
        return Result.success(map);
    }
}






