package com.qzx.recruit.service;

import com.qzx.recruit.common.Result;
import com.qzx.recruit.handler.UserDefinedException;
import com.qzx.recruit.util.ImageUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.qzx.recruit.domain.Image;
import com.qzx.recruit.mapper.ImageMapper;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qzx
 * @create 2021-10-25 18:04
 * @function
 */
@Service
public class ImageService {

    @Resource
    private ImageMapper imageMapper;


    public int deleteByPrimaryKey(Integer id) {
        return imageMapper.deleteByPrimaryKey(id);
    }


    public int insert(Image record) {
        return imageMapper.insert(record);
    }


    public Result insertSelective(MultipartFile file) {
        String s = ImageUtils.uploadImage(file);
        if(s==null){
            throw  new UserDefinedException("上传失败");
        }
        Image image=new Image();
        image.setUrl(s);
        if((imageMapper.insert(image))>0){
            return Result.success("上传成功");
        }
        return Result.error("上传失败");
    }


    public Image selectByPrimaryKey(Integer id) {
        return imageMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Image record) {
        return imageMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Image record) {
        return imageMapper.updateByPrimaryKey(record);
    }

}






