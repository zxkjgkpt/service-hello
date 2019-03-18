package com.yfny.servicehello.mapper;

import com.yfny.servicepojo.entity.CarEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * 实例用户拥有车辆详细信息carMapper
 * Created by zileShi on 2019/2/26.
 **/
public interface CarMapper extends BaseMapper<CarEntity> {

    /**
     * 根据用户id查询所有的车
     */
    @Select("select * from car where userId = #{userId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "color", column = "color"),
            @Result(property = "name", column = "name"),
            @Result(property = "userId", column = "userId"),
    })
    List<CarEntity> findCarByUserId(String userId);
}
