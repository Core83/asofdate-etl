package com.asofdate.platform.dao;

import com.asofdate.platform.entity.UserEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/18.
 */
public interface UserDao {
    List<UserEntity> findAll(String domainid);

    List<UserEntity> findAll(String domainId, String orgId, String statusCd);

    int add(UserEntity userEntity);

    int delete(JSONArray jsonArray);

    int update(UserEntity userEntity);

    int changePassword(JSONObject jsonObject);

    int changeStatus(String userId, String status);
}
