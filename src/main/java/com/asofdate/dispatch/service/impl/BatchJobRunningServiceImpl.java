package com.asofdate.dispatch.service.impl;

import com.asofdate.dispatch.dao.BatchJobRunningDao;
import com.asofdate.dispatch.entity.BatchJobStatusEntity;
import com.asofdate.dispatch.service.BatchJobRunningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
@Service
public class BatchJobRunningServiceImpl implements BatchJobRunningService {
    @Autowired
    private BatchJobRunningDao batchJobRunningDao;

    @Override
    public List<BatchJobStatusEntity> findAll(String batchId, String gid) {
        return batchJobRunningDao.findAll(batchId, gid);
    }

    @Override
    public BatchJobStatusEntity getDetails(String batchId, String gid, String tid) {
        return batchJobRunningDao.getDetails(batchId, gid, tid);
    }
}
