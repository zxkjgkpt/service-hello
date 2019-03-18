package com.yfny.servicehello.controller;

import com.yfny.servicehello.service.DemandServiceImpl;
import com.yfny.servicehello.service.RecordServiceImpl;
import com.yfny.servicepojo.entity.DemandEntity;
import com.yfny.servicepojo.entity.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求单Controller
 * <p>
 * Created  by  jinboYu  on  2019/3/6
 */
@RestController
@RequestMapping(value = "/demand")
public class DemandController {

    //需求单Service
    @Autowired
    private DemandServiceImpl demandService;

    //需求单记录Service
    @Autowired
    private RecordServiceImpl recordService;


    /**
     * 提交需求单
     * @param demandEntity
     * @return
     */
    @PostMapping("/submitDemand")
    public int submitDemand(@RequestBody DemandEntity demandEntity) {
        return demandService.submitDemand(demandEntity);
    }


    @PostMapping("/auditDemand")
    public int auditDemand(@RequestParam Long demandId,@RequestParam String taskId,@RequestParam String auditOpinion,@RequestParam String shrId,@RequestParam String orgId,@RequestParam boolean pass){
        //创建需求单实例对象
        DemandEntity demandEntity = new DemandEntity();
        //创建需求单记录实例对象
        RecordEntity recordEntity = new RecordEntity();
        if (pass){
            if (orgId!=null){
                switch (orgId){
                    case "1":
                        demandEntity.setDemandStatus("审核通过");
                        break;
                    case "2":
                        demandEntity.setDemandStatus("网级审核中");
                        break;
                    case "3":
                        demandEntity.setDemandStatus("省级审核中");
                }
            }
            demandEntity.setId(demandId);
            demandEntity.setTaskId(taskId);
            demandEntity.setAduitorId(shrId);
            recordEntity.setAuditorOpinion(auditOpinion);
            recordEntity.setDemandId(demandId);
            recordEntity.setAuditorId(shrId);
        }
        int i =demandService.auditDemand(demandEntity);
        int j= recordService.insertRecord(recordEntity);
        if (i>0&&j>0){
            return i;
        }else {
            return 0;
        }
    }

    @GetMapping("/selectDemandByUserId/{userId}/{pageNum}/{pageSize}")
    public List<DemandEntity> selectDemandByUserId(@PathVariable String userId,@PathVariable int pageNum,@PathVariable int pageSize){
        return demandService.selectDemandByUserId(userId,pageNum,pageSize);
    }
}
