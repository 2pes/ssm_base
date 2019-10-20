package com.company.project.module.sys.web;


import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.sys.model.SysRoleMenu;
import com.company.project.module.sys.service.SysRoleMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* Created by company.chen on 2019/10/20.
*/
@RestController
@RequestMapping("/module/sys/role/menu")
@Api(description = "角色菜单管理")
public class SysRoleMenuController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "角色菜单列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.sysRoleMenuService.findAll());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取角色菜单列表失败", e);
            return ResultGenerator.genFailResult("获取角色菜单列表失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "角色菜单详细数据")
    public Result detail(@RequestParam String id) {
        SysRoleMenu sysRoleMenu = sysRoleMenuService.findById(id);
        return ResultGenerator.genSuccessResult(sysRoleMenu);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "角色菜单新增")
    public Result add(SysRoleMenu sysRoleMenu) {
        try {
            sysRoleMenuService.save(sysRoleMenu);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("新增角色菜单信息失败", e);
            return ResultGenerator.genFailResult("新增角色菜单信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "角色菜单删除")
    public Result delete(@RequestParam String id) {
        try {
            sysRoleMenuService.deleteById(id);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("删除角色菜单信息失败", e);
            return ResultGenerator.genFailResult("删除角色菜单信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "角色菜单修改")
    public Result update(SysRoleMenu sysRoleMenu) {
        try {
            sysRoleMenuService.update(sysRoleMenu);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("修改角色菜单信息失败", e);
            return ResultGenerator.genFailResult("修改角色菜单信息失败，请联系网站管理员！");
        }

    }


}
