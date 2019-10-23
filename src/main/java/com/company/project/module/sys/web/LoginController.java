package com.company.project.module.sys.web;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.ExceptionResult;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysUserService;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private SysUserService sysUserService;

	@PostMapping("login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password)
			throws Exception {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			super.login(token);
		} catch (IncorrectCredentialsException ice) {
			throw new ExceptionResult(ResultGenerator.genFailResult("用户名/密码错误"));
		} catch (UnknownAccountException uae) {
			throw new ExceptionResult(ResultGenerator.genFailResult("账号不存在"));
		} catch (ExcessiveAttemptsException eae) {
			throw new ExceptionResult(ResultGenerator.genFailResult("登录过多的"));
		}
		SysUser user = super.getCurrentUser();
		super.getSession().setAttribute("user", user);
		return "index";

	}

}
