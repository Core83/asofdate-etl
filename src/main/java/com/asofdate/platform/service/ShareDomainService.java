package com.asofdate.platform.service;

import com.asofdate.platform.entity.ShareDomainEntity;
import org.json.JSONArray;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/19.
 */
public interface ShareDomainService {
    List<ShareDomainEntity> findAll(String domainId);

    List<ShareDomainEntity> unShareTarget(String domainId);

    int add(ShareDomainEntity shareDomainEntity);

    int delete(JSONArray jsonArray);

    int update(ShareDomainEntity shareDomainEntity);
}
