package com.asofdate.dispatch.dao;

import com.asofdate.dispatch.entity.GroupTaskEntity;
import com.asofdate.dispatch.entity.TaskDependencyEntity;
import org.json.JSONArray;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/27.
 */
public interface TaskDependencyDao {
    List<TaskDependencyEntity> findAll(String domainId);

    List<TaskDependencyEntity> findById(String domainId, String batchId);

    List<GroupTaskEntity> getTaskDependency(String id);

    List<GroupTaskEntity> getGroupTasks(String groupId, String id);

    int addTaskDependency(JSONArray jsonArray);

    int deleteTaskDependency(String uuid);
}
