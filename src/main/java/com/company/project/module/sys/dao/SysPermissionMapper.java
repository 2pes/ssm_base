package com.company.project.module.sys.dao;

import java.util.List;

import com.company.project.core.Mapper;
import com.company.project.module.sys.model.SysPermission;

public interface SysPermissionMapper extends Mapper<SysPermission> {
	List<SysPermission> findMenuListByUserId(String id);

	List<SysPermission> findPermissionListByUserId(String id);
}