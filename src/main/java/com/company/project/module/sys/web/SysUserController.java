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
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* Created by company.chen on 2019/10/20.
*/
@RestController
@RequestMapping("/module/sys/user")
@Api(description = "用户管理")
public class SysUserController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysUserService sysUserService;

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "用户列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.sysUserService.findAll());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取用户列表失败", e);
            return ResultGenerator.genFailResult("获取用户列表失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "用户详细数据")
    public Result detail(@RequestParam String id) {
        SysUser sysUser = sysUserService.findById(id);
        return ResultGenerator.genSuccessResult(sysUser);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "用户新增")
    public Result add(SysUser sysUser) {
        try {
            sysUserService.save(sysUser);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("新增用户信息失败", e);
            return ResultGenerator.genFailResult("新增用户信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "用户删除")
    public Result delete(@RequestParam String id) {
        try {
            sysUserService.deleteById(id);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("删除用户信息失败", e);
            return ResultGenerator.genFailResult("删除用户信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "用户修改")
    public Result update(SysUser sysUser) {
        try {
            sysUserService.update(sysUser);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("修改用户信息失败", e);
            return ResultGenerator.genFailResult("修改用户信息失败，请联系网站管理员！");
        }

    }


}
