package com.company.project.module.sys.web;

import com.company.project.configurer.shiro.ProjectRealm;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.module.sys.service.SysUserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SysLoginController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ProjectRealm projectRealm;

/*
	@PostMapping("loginto")
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

	}*/

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        // 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的权限定名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        ModelAndView view = new ModelAndView("login");
        // 根据shiro返回的异常类路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            /*
             * Result result = new Result(); result.setType(Result.TYPE_RESULT_FAIL);
             */
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                // 最终会抛给异常处理器
                /*
                 * result.setMessage("账号不存在"); throw new ExceptionResult(result);
                 */
                view.addObject("off_msg", "账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                /*
                 * result.setMessage("用户名/密码错误"); throw new ExceptionResult(result);
                 */
                view.addObject("off_msg", "用户名/密码错误");
            } else if ("randomCodeError".equals(exceptionClassName)) {
                /*
                 * result.setMessage("验证码错误 "); throw new ExceptionResult(result);
                 */
                view.addObject("off_msg", "验证码错误");
            } else {
                view.addObject("off_msg", "未知错误，请联系管理员！");
                /*
                 * throw new Exception();// 最终在异常处理器生成未知错误
                 */

            }
        }
        // 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
        // 登陆失败还到login页面

        return view;

    }


    @RequestMapping("/clearShiroCache")
    public String clearShiroCache() {

        //清除缓存，将来正常开发要在service调用customRealm.clearCached()
        projectRealm.clearCached();
        return "success";
    }
}
