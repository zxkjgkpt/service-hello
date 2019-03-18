package com.yfny.servicehello.mapper;

import com.yfny.servicepojo.entity.DemandEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * 需求单Mapper
 * <p>
 * Created  by  jinboYu  on  2019/3/6
 */
public interface DemandMapper extends BaseMapper<DemandEntity> {


    @Select("SELECT * FROM demand WHERE CREATEBY_ID = #{createId}")
    @Results({
            @Result(id = true, column = "ID", property = "id"),
            @Result(column = "CREATEBY_NAME",property = "demandName"),
            @Result(column = "DEMAND_NAME",property = "createByName"),
            @Result(column = "DEMAND_DESCRIPTION",property = "demandDescription"),
            @Result(column = "DEMAND_STATUS",property = "demandStatus"),
            @Result(column = "AUDITOR_ID",property = "aduitorId"),
            @Result(column = "ORG_ID",property = "orgId"),
            @Result(column = "CREATEBY_ID",property = "createById"),
            @Result(column = "TASK_ID",property = "taskId")
    })
    List<DemandEntity> selectDemandByUserId(String createId);
}
