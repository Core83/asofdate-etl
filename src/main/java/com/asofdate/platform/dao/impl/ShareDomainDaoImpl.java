package com.asofdate.platform.dao.impl;

import com.asofdate.platform.dao.ShareDomainDao;
import com.asofdate.platform.entity.ShareDomainEntity;
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
 * Created by hzwy23 on 2017/6/19.
 */
@Repository
public class ShareDomainDaoImpl implements ShareDomainDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ShareDomainEntity> findAll(String domainId) {
        RowMapper<ShareDomainEntity> rowMapper = new BeanPropertyRowMapper<>(ShareDomainEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_083, rowMapper, domainId);
    }

    @Override
    public List<ShareDomainEntity> unShareTarget(String domainId) {
        RowMapper<ShareDomainEntity> rowMapper = new BeanPropertyRowMapper<>(ShareDomainEntity.class);
        return jdbcTemplate.query(SqlDefine.sys_rdbms_085, rowMapper, domainId, domainId);
    }

    @Override
    public int add(ShareDomainEntity shareDomainEntity) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_086,
                shareDomainEntity.getDomain_id(),
                shareDomainEntity.getTarget_domain_id(),
                shareDomainEntity.getAuthorization_level(),
                shareDomainEntity.getCreate_user(),
                shareDomainEntity.getModify_user());
    }

    @Transactional
    @Override
    public int delete(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject m = (JSONObject) jsonArray.get(i);
            jdbcTemplate.update(SqlDefine.sys_rdbms_087,
                    m.getString("uuid"));
        }
        return 1;
    }

    @Override
    public int update(ShareDomainEntity shareDomainEntity) {
        return jdbcTemplate.update(SqlDefine.sys_rdbms_088,
                shareDomainEntity.getAuthorization_level(),
                shareDomainEntity.getModify_user(),
                shareDomainEntity.getUuid());
    }
}
