package com.company.project.module.sys.service;

import com.company.project.module.sys.model.SysPermission;
import com.company.project.module.sys.model.SysUser;

import java.util.List;

import com.company.project.core.Service;

/**
 * Created by company.chen on 2019/10/20.
 */
public interface SysUserService extends Service<SysUser> {

	/**
	 * 根据用户名id查询用户
	 * 
	 * @param userCode
	 * @return
	 * @throws Exception 
	 */
	SysUser findSysUserByUserCode(String userCode) throws Exception;

	/**
	 * 根据id查菜单
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	List<SysPermission> findMenuListByUserId(String id) throws Exception;

	/**
	 * 根据id查权限
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	List<SysPermission> findPermissionListByUserId(String id) throws Exception;

}
