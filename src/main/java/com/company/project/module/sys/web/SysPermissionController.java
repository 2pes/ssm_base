package com.company.project.module.sys.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.company.project.core.model.EasyUIDataGridResult;
import com.company.project.core.model.EasyUITreeGridNode;
import com.company.project.core.model.EasyUITreeNode;
import com.company.project.module.sys.model.ActiveUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.sys.model.SysPermission;
import com.company.project.module.sys.service.SysPermissionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by company.chen on 2019/10/20.
 */
@RestController
@RequestMapping("/module/sys/permission")
@Api(description = "权限管理")
public class SysPermissionController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysPermissionService sysPermissionService;

    @GetMapping()
    @ApiOperation(value = "请求地址", notes = "菜单权限列表页面地址")
    public ModelAndView listPage() {
        return new ModelAndView("/module/sys/permission/list");
    }
    @GetMapping("/edit")
    @ApiOperation(value = "请求地址", notes = "菜单修改页面地址")
    public ModelAndView editPage() {
        return new ModelAndView("/module/sys/permission/edit");
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "权限列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.sysPermissionService.findAll());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取权限列表失败", e);
            return ResultGenerator.genFailResult("获取权限列表失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "权限详细数据")
    public Result detail(String id) {
        SysPermission sysPermission = sysPermissionService.findById(id);
        return ResultGenerator.genSuccessResult(sysPermission);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "权限新增")
    public Result add(SysPermission sysPermission) {
        try {
            sysPermissionService.save(sysPermission);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增权限信息失败", e);
            return ResultGenerator.genFailResult("新增权限信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "权限删除")
    public Result delete(@RequestParam String id) {
        try {
            sysPermissionService.deleteById(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除权限信息失败", e);
            return ResultGenerator.genFailResult("删除权限信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "权限修改")
    public Result update(SysPermission sysPermission) {
        try {
            sysPermissionService.update(sysPermission);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改权限信息失败", e);
            return ResultGenerator.genFailResult("修改权限信息失败，请联系网站管理员！");
        }

    }

    @PostMapping("/getMenusByUser")
    @ApiOperation(value = "查询树形菜单", notes = "根据角色查询树形菜单")
    public Result getMenusByUser()  {
        ActiveUser currentUser = super.getCurrentUser();
        List<EasyUITreeNode<EasyUITreeNode>> result = sysPermissionService.getMenusByUser(currentUser);
        return ResultGenerator.genSuccessResult(result);
    }

    @PostMapping("/treeGrid")
    @ApiOperation(value = "列表", notes = "权限列表")
    public Result getTreeGridList(QueryRequest request) {
        try {
            List<EasyUITreeGridNode> list = sysPermissionService.getTreeGridList(request.getParams());
            EasyUIDataGridResult easyUIDataGridResult = EasyUIDataGridResult.builder().rows(list).total(list.size()).build();
            return ResultGenerator.genSuccessResult(easyUIDataGridResult);
        } catch (Exception e) {
            logger.error("获取权限列表失败", e);
            return ResultGenerator.genFailResult("获取权限列表失败！");
        }
    }
    @PostMapping("/getTreeByRole")
    @ApiOperation(value = "列表", notes = "权限树形数据，用于角色")
    public Result getTreeList(QueryRequest request) {
        try {
            List<EasyUITreeGridNode> list = sysPermissionService.getTreeGridList(request.getParams());
            EasyUIDataGridResult easyUIDataGridResult = EasyUIDataGridResult.builder().rows(list).total(list.size()).build();
            return ResultGenerator.genSuccessResult(easyUIDataGridResult);
        } catch (Exception e) {
            logger.error("获取权限列表失败", e);
            return ResultGenerator.genFailResult("获取权限列表失败！");
        }
    }
}
