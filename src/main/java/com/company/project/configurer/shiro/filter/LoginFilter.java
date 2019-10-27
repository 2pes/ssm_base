package com.company.project.configurer.shiro.filter;

import com.company.project.configurer.shiro.util.ShiroFilterUtils;
import com.company.project.core.ResultGenerator;
import com.company.project.module.sys.model.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 判断登录过滤器
 *
 * @author Chen
 * @created 2019-10-26-10:30.
 */
public class LoginFilter extends AccessControlFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 是否允许访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        ActiveUser user = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

        //ajax请求
        if (!ShiroFilterUtils.isAjax(request)) {
            //保存登陆之前地址
            saveRequest(request);
        }
        //Session未失效时验证通过
        if (null != user || isLoginRequest(request, response)) {
            return Boolean.TRUE;
        }
        //FALSE Session失效，切实非AJAX请求，验证是否，调用onAccessDenied跳转到登录页面
        return Boolean.FALSE;

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ActiveUser user = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (null != user || isLoginRequest(request, response)) {
            return Boolean.TRUE;
        }
        // ajax请求
        if (ShiroFilterUtils.isAjax(request)) {
            logger.info("当前用户没有登录，并且是Ajax请求！");
            ShiroFilterUtils.out(response, ResultGenerator.genFailResult("请重新登录！"));
            return Boolean.FALSE;
        }

        //保存Request和Response 到登录后的链接
        saveRequestAndRedirectToLogin(request, response);
        return Boolean.FALSE;
    }
}
