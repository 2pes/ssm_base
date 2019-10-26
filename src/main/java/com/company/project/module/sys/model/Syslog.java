package com.company.project.module.sys.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_log")
public class Syslog {
    @Id
    private String id;

    /**
     * 操作用户
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    @Column(name = "operate_time")
    private Date operateTime;

    /**
     * 最后一次更新者的ip地址
     */
    @Column(name = "operate_ip")
    private String operateIp;

    /**
     * 耗时
     */
    private Integer time;

    /**
     * 操作地点
     */
    private String location;

    /**
     * 操作
     */
    private String operation;

    /**
     * 执行方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取操作用户
     *
     * @return user_name - 操作用户
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置操作用户
     *
     * @param userName 操作用户
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后一次更新时间
     *
     * @return operate_time - 最后一次更新时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置最后一次更新时间
     *
     * @param operateTime 最后一次更新时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取最后一次更新者的ip地址
     *
     * @return operate_ip - 最后一次更新者的ip地址
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * 设置最后一次更新者的ip地址
     *
     * @param operateIp 最后一次更新者的ip地址
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    /**
     * 获取耗时
     *
     * @return time - 耗时
     */
    public Integer getTime() {
        return time;
    }

    /**
     * 设置耗时
     *
     * @param time 耗时
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * 获取操作地点
     *
     * @return location - 操作地点
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置操作地点
     *
     * @param location 操作地点
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取操作
     *
     * @return operation - 操作
     */
    public String getOperation() {
        return operation;
    }

    /**
     * 设置操作
     *
     * @param operation 操作
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * 获取执行方法
     *
     * @return method - 执行方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置执行方法
     *
     * @param method 执行方法
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取请求参数
     *
     * @return params - 请求参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置请求参数
     *
     * @param params 请求参数
     */
    public void setParams(String params) {
        this.params = params;
    }
}