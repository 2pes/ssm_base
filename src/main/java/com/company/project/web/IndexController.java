package com.company.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	private String list(Model model) {

		return "list";// WEB-INF/jsp/"list".jsp
	}

}
