package com.company.project.configurer.shiro.util;

import com.company.project.module.sys.model.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * shiro 认证信息操作工具类
 *
 * @author Chen
 * @created 2019-10-26-10:43.
 */
public class ShiroManagerUtils {
    /**
     * 获取shiro的session
     *
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取shiro Subject
     *
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取用户
     *
     * @return
     */
    public static ActiveUser getActiveUser() {
        return (ActiveUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取用户id
     *
     * @return
     */
    public static String getUserId() {
        return getActiveUser() == null ? null : getActiveUser().getUserid();
    }


    public static String getUserNickName() {
        return getActiveUser() == null ? null : getActiveUser().getUsername();
    }

    public static String getUsercode() {
        return getActiveUser() == null ? null : getActiveUser().getUsercode();
    }

    /**
     * 把值放入到当前登录用户的Session里
     *
     * @param key
     * @param value
     */
    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 从当前登录用户的Session里取值
     *
     * @param key
     * @return
     */
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 从当前登录用户的Session里删除属性
     *
     * @param key
     * @return
     */
    public static Object delSessionAttribute(Object key) {
        return getSession().removeAttribute(key);
    }

    /**
     * 判断是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return null != SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 退出登录
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取验证码，获取后删除
     *
     * @return
     */
    public static String getKaptcha(String key) {
        String kaptcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return kaptcha;
    }
}
