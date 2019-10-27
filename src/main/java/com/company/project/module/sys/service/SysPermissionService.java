package com.company.project.module.sys.service;
import com.company.project.core.model.EasyUITreeGridNode;
import com.company.project.core.model.EasyUITreeNode;
import com.company.project.module.sys.model.ActiveUser;
import com.company.project.module.sys.model.SysPermission;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by company.chen on 2019/10/20.
 */
public interface SysPermissionService extends Service<SysPermission> {

    /**
     * 根据用户角色查询菜单
     * @param currentUser
     * @return
     */
    List<EasyUITreeNode<EasyUITreeNode>> getMenusByUser(ActiveUser currentUser);

    /**
     * 树形菜单表格
     * @return
     */
    List<EasyUITreeGridNode> getTreeGridList();
}
