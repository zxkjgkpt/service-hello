package com.yfny.servicehello.service;

import com.yfny.servicehello.mapper.RecordMapper;
import com.yfny.servicepojo.entity.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 需求单流程记录Service
 * <p>
 * Created  by  jinboYu  on  2019/3/8
 */
@Service
public class RecordServiceImpl {

    @Autowired
    private RecordMapper recordMapper;

    public int insertRecord(RecordEntity recordEntity){
        return recordMapper.insertSelective(recordEntity);
    }
}
