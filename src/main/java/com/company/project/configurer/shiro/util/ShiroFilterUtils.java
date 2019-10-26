package com.company.project.configurer.shiro.util;

import com.company.project.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Shiro Filter 工具类
 *
 * @author Chen
 * @created 2019-10-26-10:34.
 */
public class ShiroFilterUtils {
    private static Logger logger = LoggerFactory.getLogger(ShiroFilterUtils.class);

    /**
     * 是否是Ajax请求,如果是ajax请求响应头会有，x-requested-with
     *
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

    /**
     * response 设置超时
     */
    public static void out(ServletResponse servletResponse, Result result) throws Exception {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        //在响应头设置session状态
        response.setHeader("session-status", "timeout");
        response.setStatus(200);
        try {
            response.getWriter().write(result.toString());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            if (response.getWriter() != null) {
                response.getWriter().flush();
                response.getWriter().close();
            }
        }

    }

}
