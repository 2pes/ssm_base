package com.company.project.module.sys.web;

import com.company.project.core.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.project.module.sys.model.ActiveUser;

@Controller
public class IndexController extends BaseController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String index(Model model) {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String indexPage(Model model) {
        // 通过model传到页面
        model.addAttribute("activeUser", super.getCurrentUser());
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private String hello(Model model) {

        return "hello";
    }

    @RequestMapping(value = "/refuse", method = RequestMethod.GET)
    private String refuse() {
        return "refuse";
    }

    @RequestMapping(value = "/validatecode", method = RequestMethod.GET)
    private String validatecode() {
        return "validatecode";
    }

    @RequestMapping(value = "/sys/dialog", method = RequestMethod.GET)
    private String dialog(Model model) {

        return "module/sys/dialog";
    }

    @RequestMapping(value = "/toDruid", method = RequestMethod.GET)
    private String druidPage() {
        return "module/config/druid";
    }
}
