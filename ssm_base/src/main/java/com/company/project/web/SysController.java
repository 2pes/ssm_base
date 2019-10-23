package com.company.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sys")
public class SysController {

	@RequestMapping(value = "/dialog", method = RequestMethod.GET)
	private String dialog(Model model) {

		return "module/sys/dialog";
	}

}
