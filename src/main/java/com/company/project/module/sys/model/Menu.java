package com.company.project.module.sys.model;

import javax.persistence.*;

public class Menu {
    /**
     * 编号
     */
    @Id
    private String menuid;

    /**
     * 名称
     */
    private String menuname;

    /**
     * 对应URL
     */
    private String url;

    /**
     * 图标样式
     */
    private String icon;

    /**
     * 上一级菜单编号
     */
    private String pid;

    /**
     * 该菜单是否为父菜单，1为true，0为false
     */
    @Column(name = "is_parent")
    private Boolean isParent;

    /**
     * 获取编号
     *
     * @return menuid - 编号
     */
    public String getMenuid() {
        return menuid;
    }

    /**
     * 设置编号
     *
     * @param menuid 编号
     */
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    /**
     * 获取名称
     *
     * @return menuname - 名称
     */
    public String getMenuname() {
        return menuname;
    }

    /**
     * 设置名称
     *
     * @param menuname 名称
     */
    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    /**
     * 获取对应URL
     *
     * @return url - 对应URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置对应URL
     *
     * @param url 对应URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取图标样式
     *
     * @return icon - 图标样式
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标样式
     *
     * @param icon 图标样式
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取上一级菜单编号
     *
     * @return pid - 上一级菜单编号
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置上一级菜单编号
     *
     * @param pid 上一级菜单编号
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取该菜单是否为父菜单，1为true，0为false
     *
     * @return is_parent - 该菜单是否为父菜单，1为true，0为false
     */
    public Boolean getIsParent() {
        return isParent;
    }

    /**
     * 设置该菜单是否为父菜单，1为true，0为false
     *
     * @param isParent 该菜单是否为父菜单，1为true，0为false
     */
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }
}