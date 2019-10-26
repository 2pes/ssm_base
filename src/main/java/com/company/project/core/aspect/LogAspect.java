package com.company.project.core.aspect;

import com.company.project.configurer.shiro.util.ShiroManagerUtils;
import com.company.project.core.util.HttpContextUtils;
import com.company.project.core.util.IPUtils;
import com.company.project.module.sys.model.ActiveUser;
import com.company.project.module.sys.model.Syslog;
import com.company.project.module.sys.service.SyslogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Chen
 * @created 2019-10-26-13:10.
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyslogService logService;

    @Pointcut("@annotation(com.company.project.core.annotation.Log)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Exception {
        Object result = null;
        StopWatch watch = new StopWatch();
        watch.start();

        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage());
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        String ip = IPUtils.getIpAddr(request);

        ActiveUser user = ShiroManagerUtils.getActiveUser();
        Syslog log = new Syslog();
        log.setUserName(user.getUsername());
        log.setOperateIp(ip);
        log.setTime(watch.getTaskCount());
        logService.saveLog(point, log);
        watch.stop();
        System.out.println(watch.prettyPrint());

        return result;
    }
}
