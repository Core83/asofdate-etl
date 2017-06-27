package com.asofdate.dispatch.dao;

import com.asofdate.dispatch.entity.BatchGroupEntity;
import com.asofdate.dispatch.entity.GroupDependencyEntity;
import org.json.JSONArray;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/27.
 */
public interface GroupDependencyDao {
    List<GroupDependencyEntity> findAll(String domainId);

    List<GroupDependencyEntity> findById(String domainId, String batchId);

    List<BatchGroupEntity> getGroupDependency(String id);

    int deleteGroupDependency(String uuid);

    int addGroupDependency(JSONArray jsonArray);
}
