package com.asofdate.dispatch.service.impl;

import com.asofdate.dispatch.dao.BatchHistoryDao;
import com.asofdate.dispatch.dto.BatchHistoryDTO;
import com.asofdate.dispatch.entity.BatchHistoryEntity;
import com.asofdate.dispatch.service.BatchHistoryService;
import com.asofdate.utils.RetMsg;
import com.asofdate.utils.SysStatus;
import com.asofdate.utils.factory.RetMsgFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/16.
 */
@Service
public class BatchHistoryServiceImpl implements BatchHistoryService {
    @Autowired
    private BatchHistoryDao batchHistoryDao;

    @Override
    public List<BatchHistoryEntity> findAll(String domainId) {
        return batchHistoryDao.findAll(domainId);
    }

    @Override
    public RetMsg delete(List<BatchHistoryDTO> list) {
        List<BatchHistoryEntity> args = new ArrayList<>();
        for (BatchHistoryDTO m:list){
            BatchHistoryEntity entity = new BatchHistoryEntity();
            entity.setUuid(m.getUuid());
            args.add(entity);
        }
        try {
            int size = batchHistoryDao.delete(args);
            if (1 == size){
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE,"success",null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE,"删除批次历史信息失败，请联系管理员",null);
        } catch (Exception e){
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE,e.getMessage(),null);
        }
    }
}
