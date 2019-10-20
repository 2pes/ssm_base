package com.company.project.module.sys.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.module.sys.dao.SysUserMapper;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysUserService;


/**
 * Created by company.chen on 2019/10/20.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysUserMapper sysUserMapper;

}
