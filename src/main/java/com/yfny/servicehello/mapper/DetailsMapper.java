package com.yfny.servicehello.mapper;


import com.yfny.servicepojo.entity.DetailsEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;


/**
 * 实例用户详细信息Mapper
 * Created by zileShi on 2019/2/26.
 **/
public interface DetailsMapper extends BaseMapper<DetailsEntity> {

    @Select("select * from details where userId = #{userId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "address", column = "address"),
            @Result(property = "userId", column = "userId"),
    })
    public DetailsEntity getDetailsByUserId(String userId);

}
