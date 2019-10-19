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
import com.company.project.module.sys.model.Menu;
import com.company.project.module.sys.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* Created by company.chen on 2019/10/19.
*/
@RestController
@RequestMapping("/module/menu")
@Api(description = "菜单管理")
public class MenuController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MenuService menuService;

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "菜单列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.menuService.findAll());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取菜单列表失败", e);
            return ResultGenerator.genFailResult("获取菜单列表失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "菜单详细数据")
    public Result detail(@RequestParam String id) {
        Menu menu = menuService.findById(id);
        return ResultGenerator.genSuccessResult(menu);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "菜单新增")
    public Result add(Menu menu) {
        try {
            menuService.save(menu);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("新增菜单信息失败", e);
            return ResultGenerator.genFailResult("新增菜单信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "菜单删除")
    public Result delete(@RequestParam String id) {
        try {
            menuService.deleteById(id);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("删除菜单信息失败", e);
            return ResultGenerator.genFailResult("删除菜单信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "菜单修改")
    public Result update(Menu menu) {
        try {
            menuService.update(menu);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("修改菜单信息失败", e);
            return ResultGenerator.genFailResult("修改菜单信息失败，请联系网站管理员！");
        }

    }


}
