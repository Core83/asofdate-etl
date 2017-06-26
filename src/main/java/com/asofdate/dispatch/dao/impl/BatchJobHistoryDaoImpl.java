package com.asofdate.dispatch.dao.impl;

import com.asofdate.dispatch.dao.BatchJobHistoryDao;
import com.asofdate.dispatch.entity.BatchJobHistoryEntity;
import com.asofdate.sql.SqlDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
@Repository
public class BatchJobHistoryDaoImpl implements BatchJobHistoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BatchJobHistoryEntity> findAll(String uuid, String gid) {
        RowMapper<BatchJobHistoryEntity> rowMapper = new BeanPropertyRowMapper<>(BatchJobHistoryEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_200, rowMapper, uuid, gid);
    }
}
