package com.company.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String index(Model model) {

		return "redirect:/hello";// WEB-INF/jsp/"list".jsp
	}


	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	private String hello(Model model) {

		return "hello";// WEB-INF/jsp/"list".jsp
	}

}
