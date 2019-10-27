package com.company.project.configurer.quartz;


import com.google.common.base.Stopwatch;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Spring 整合 Quartz 的  Hello World!
 *
 * @author dufy
 * @date 2017.02.26
 */
public class TaskOneJob implements Job {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        logger.info("This is a first spring combine quartz !");
        logger.info("Welcome to Spring_Quartz World!" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        logger.info("Let's begin ! \n \n");
        stopwatch.stop();
        System.out.println(stopwatch.prettyPrint());
    }

}

