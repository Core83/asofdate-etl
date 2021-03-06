package com.asofdate.platform.service;

import com.asofdate.platform.entity.OrgEntity;
import org.json.JSONArray;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public interface OrgService {
    List<OrgEntity> findAll(String domainId);

    List<OrgEntity> findSub(String domainId, String orgId);

    int add(OrgEntity orgEntity);

    int delete(JSONArray jsonArray);

    int update(OrgEntity orgEntity);
}
