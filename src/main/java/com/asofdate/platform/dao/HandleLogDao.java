package com.asofdate.platform.dao;

import com.asofdate.platform.entity.HandleLogEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public interface HandleLogDao {
    List<HandleLogEntity> findAll(String domainId);

    List<HandleLogEntity> findAll(String domainId, Integer offset, Integer limit);

    Integer getTotal(String domainId);
}
