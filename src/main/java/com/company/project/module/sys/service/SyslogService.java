package com.company.project.module.sys.service;

import com.company.project.core.Service;
import com.company.project.module.sys.model.Syslog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;


/**
 * Created by company.chen on 2019/10/26.
 */
public interface SyslogService extends Service<Syslog> {
    /**
     * 根据条件查询
     *
     * @param map
     * @return
     */
    List<Syslog> findAllLogs(Map map);

    /**
     * 日志记录
     *
     * @param point
     * @param log
     */
    @Async
    void saveLog(ProceedingJoinPoint point, Syslog log) throws Exception;
}
