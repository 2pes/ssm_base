package com.company.project.module.sys.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.model.EasyUITreeGridNode;
import com.company.project.core.model.EasyUITreeNode;
import com.company.project.core.util.ProjectUtils;
import com.company.project.core.util.TreeUtils;
import com.company.project.module.sys.dao.SysPermissionMapper;
import com.company.project.module.sys.model.ActiveUser;
import com.company.project.module.sys.model.SysPermission;
import com.company.project.module.sys.service.SysPermissionService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by company.chen on 2019/10/20.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysPermissionServiceImpl extends AbstractService<SysPermission> implements SysPermissionService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<EasyUITreeNode<EasyUITreeNode>> getMenusByUser(ActiveUser currentUser) {
        List<SysPermission> menus = currentUser.getMenus();
        ArrayList<EasyUITreeNode<EasyUITreeNode>> menusList = Lists.newArrayList();
        menus.forEach(permission -> {
            EasyUITreeNode node = EasyUITreeNode.builder()
                    .attributes(ProjectUtils.objectToMap(permission))
                    .id(permission.getId().toString())
                    .iconCls(null)
                    .pid(permission.getParentid().toString())
                    .text(permission.getName())
                    .build();
            if (!StringUtils.isEmpty(permission.getUrl())) {
                node.setUrl(permission.getUrl());
            }
            //todo 判断他有没有子项
            if (permission.getParentids() == null) {
                // node.setChildren();
            }
            menusList.add(node);
        });
        return TreeUtils.buildEasyUITreeNodeList(menusList, "1");
    }

    @Override
    public List<EasyUITreeGridNode> getTreeGridList() {
        List<EasyUITreeGridNode> list = new ArrayList<>();
        Condition condition = new Condition(SysPermission.class);
        condition.createCriteria().andNotEqualTo("parentid", 0);
        // condition.setOrderByClause("seq asc");
        List<SysPermission> allPermissions = sysPermissionMapper.selectByCondition(condition);
        for (SysPermission permission : allPermissions) {
            if (permission.getParentid().toString().equals("0")) {
                EasyUITreeGridNode treeGridNode = setEasyUITreeGridNode(permission);
                treeGridNode.set_parentId(null);
                list.add(treeGridNode);
            } else {
                EasyUITreeGridNode treeGridNode = setEasyUITreeGridNode(permission);
                list.add(treeGridNode);
            }
        }
        return list;
    }

    /**
     * 设置菜单属性
     */
    public EasyUITreeGridNode setEasyUITreeGridNode(SysPermission permission) {
        EasyUITreeGridNode treeGridNode = EasyUITreeGridNode.
                builder()
                .id(permission.getId().toString())
                .url(permission.getUrl())
                .name(permission.getName())
                ._parentId(permission.getParentid().toString())
                //.icon(permission.getIcon())
                .type(permission.getType() == "permission" ? 1 : 0)
                //.status(permission.())
                .build();
        return treeGridNode;
    }
}
