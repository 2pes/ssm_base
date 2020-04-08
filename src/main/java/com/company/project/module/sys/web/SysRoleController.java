package com.company.project.module.sys.web;


import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.sys.model.SysRole;
import com.company.project.module.sys.service.SysRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by company.chen on 2019/10/27.
 */
@RestController
@RequestMapping("/sys/role")
@Api(description = "角色管理")
public class SysRoleController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysRoleService sysRoleService;

    @GetMapping()
    @ApiOperation(value = "请求地址", notes = "角色列表地址")
    public ModelAndView listPage() {
        return new ModelAndView("/module/sys/role/list");
    }

    @GetMapping("/edit")
    @ApiOperation(value = "请求地址", notes = "角色修改页面地址")
    public ModelAndView editPage() {
        return new ModelAndView("/module/sys/role/edit");
    }

    @GetMapping("/editPerm")
    @ApiOperation(value = "请求地址", notes = "权限分配修改页面地址")
    public ModelAndView editPermPage() {
        return new ModelAndView("/module/sys/role/editPerm");
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "角色列表")
    public Result list(QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.sysRoleService.findAllRole(request.getParams()));
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取角色列表失败", e);
            return ResultGenerator.genFailResult("获取角色列表失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "角色详细数据")
    public Result detail(@RequestParam String id) {
        SysRole sysRole = sysRoleService.findById(id);
        return ResultGenerator.genSuccessResult(sysRole);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "角色新增")
    public Result add(SysRole sysRole) {
        try {
            sysRoleService.save(sysRole);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("新增角色信息失败", e);
            return ResultGenerator.genFailResult("新增角色信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "角色删除")
    public Result delete(@RequestParam String id) {
        try {
            sysRoleService.deleteById(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("删除角色信息失败", e);
            return ResultGenerator.genFailResult("删除角色信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "角色修改")
    public Result update(SysRole sysRole) {
        try {
            sysRoleService.update(sysRole);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            logger.error("修改角色信息失败", e);
            return ResultGenerator.genFailResult("修改角色信息失败，请联系网站管理员！");
        }

    }


}
