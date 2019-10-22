package com.company.project.module.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.module.sys.dao.SysPermissionMapper;
import com.company.project.module.sys.dao.SysUserMapper;
import com.company.project.module.sys.model.SysPermission;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysUserService;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by company.chen on 2019/10/20.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Override
	public SysUser findSysUserByUserCode(String userCode) throws Exception {
		Example userExample = new Example(SysUser.class);
		userExample.createCriteria().andEqualTo("usercode", userCode);
		List<SysUser> list = sysUserMapper.selectByExample(userExample);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SysPermission> findMenuListByUserId(String id) throws Exception {
		return sysPermissionMapper.findMenuListByUserId(id);
	}

	@Override
	public List<SysPermission> findPermissionListByUserId(String id) throws Exception {
		return sysPermissionMapper.findPermissionListByUserId(id);
	}

}
