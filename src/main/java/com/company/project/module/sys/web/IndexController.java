package com.company.project.module.sys.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.project.module.sys.model.ActiveUser;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String index(Model model) {

		return "redirect:/index";// WEB-INF/jsp/"list".jsp
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String indexPage(Model model) {
		// 从shiro的session中取activeUser
		Subject subject = SecurityUtils.getSubject();
		// 取身份信息
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		// 通过model传到页面
		model.addAttribute("activeUser", activeUser);

		return "index";// WEB-INF/jsp/"list".jsp
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	private String hello(Model model) {

		return "hello";// WEB-INF/jsp/"list".jsp
	}

}
