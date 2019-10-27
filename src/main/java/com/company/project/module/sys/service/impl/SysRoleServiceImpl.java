package com.company.project.module.sys.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.sys.dao.SysRoleMapper;
import com.company.project.module.sys.model.SysRole;
import com.company.project.module.sys.service.SysRoleService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by company.chen on 2019/10/27.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List findAllRole(Map params) {
        List<SysRole> list = Lists.newArrayList();
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        if (!CollectionUtils.isEmpty(params)) {
            String name = String.valueOf(params.get("name")).trim();

            if (!StringUtils.isEmpty(name)) {
                criteria.andLike("name", "%" + name + "%");
            }
            if (!StringUtils.isEmpty(params.get("available"))) {
                criteria.andEqualTo("available", Long.parseLong(String.valueOf(params.get("available"))));
            }
        }
        list = sysRoleMapper.selectByExample(example);
        return list;
    }
}
