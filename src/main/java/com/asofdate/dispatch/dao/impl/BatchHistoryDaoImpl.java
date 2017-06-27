package com.asofdate.dispatch.dao.impl;

import com.asofdate.dispatch.dao.BatchHistoryDao;
import com.asofdate.dispatch.entity.BatchHistoryEntity;
import com.asofdate.sql.SqlDefine;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/16.
 */
@Repository
public class BatchHistoryDaoImpl implements BatchHistoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BatchHistoryEntity> findAll(String domainId) {
        RowMapper<BatchHistoryEntity> rowMapper = new BeanPropertyRowMapper<>(BatchHistoryEntity.class);
        List<BatchHistoryEntity> list = jdbcTemplate.query(SqlDefine.sys_rdbms_193, rowMapper, domainId);
        return list;
    }

    @Transactional
    @Override
    public int delete(List<BatchHistoryEntity> list) {
        for (BatchHistoryEntity m:list) {
            int size = jdbcTemplate.update(SqlDefine.sys_rdbms_194, m.getUuid());
            if (size != 1) {
                return -1;
            }
        }
        return 1;
    }
}
