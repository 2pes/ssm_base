package com.company.project.test.quartz;

import com.company.project.configurer.quartz.HelloWorldJob;
import com.company.project.test.BaseTest;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 参考https://blog.csdn.net/u010648555/article/details/54863144
 * @author Chen
 * @created 2019-10-26-22:20.
 */
public class JobTest extends BaseTest {
    @Autowired
    private Scheduler scheduler;


    @Test
    public void startSchedule() {
        try {
            // 1、创建一个JobDetail实例，指定Quartz
            JobDetail jobDetail = JobBuilder.newJob(HelloWorldJob.class)
                    // 任务执行类
                    .withIdentity("job1_1", "jGroup1")
                    // 任务名，任务组
                    .build();

            // 触发器类型
            //SimpleScheduleBuilder builder = SimpleScheduleBuilder
            // 设置执行次数
            //.repeatSecondlyForTotalCount(5);

            CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
            // 2、创建Trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1_1", "tGroup1").startNow()
                    .withSchedule(builder)
                    .build();

            // 3、创建Scheduler
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            // 4、调度执行
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
