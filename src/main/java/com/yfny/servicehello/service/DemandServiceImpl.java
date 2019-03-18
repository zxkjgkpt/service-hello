package com.yfny.servicehello.service;

import com.github.pagehelper.PageHelper;
import com.yfny.servicehello.mapper.DemandMapper;
import com.yfny.servicepojo.entity.DemandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 需求单Service
 * <p>
 * Created  by  jinboYu  on  2019/3/6
 */
@Service
public class DemandServiceImpl {

    @Autowired
    private DemandMapper demandMapper;

    /**
     * 提交需求单
     * @param demandEntity
     * @return
     */
    public int submitDemand(DemandEntity demandEntity){
        if (demandEntity.getOrgId()!=null){
            switch (demandEntity.getOrgId()){
                case "1":
                    demandEntity.setDemandStatus("网级审核中");
                    break;
                case "2":
                    demandEntity.setDemandStatus("省级审核中");
                    break;
                case "3":
                    demandEntity.setDemandStatus("地级审核中");
                    break;
            }
        }
        return demandMapper.insertSelective(demandEntity);
    }

    //审核需求单
    @Transactional
    public int auditDemand(DemandEntity demandEntity){
        return demandMapper.updateByPrimaryKeySelective(demandEntity);
    }

    //根据创建人查询需求单
    public List<DemandEntity> selectDemandByUserId(String createId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<DemandEntity> demandEntityList = demandMapper.selectDemandByUserId(createId);
        return demandEntityList;
    }
}
