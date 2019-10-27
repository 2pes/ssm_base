package com.company.project.module.sys.service;

import com.company.project.module.sys.model.SysRole;
import com.company.project.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by company.chen on 2019/10/27.
 */
public interface SysRoleService extends Service<SysRole> {
    /**
     * 查询
     *
     * @param params
     * @return
     */
    List findAllRole(Map params);
}
