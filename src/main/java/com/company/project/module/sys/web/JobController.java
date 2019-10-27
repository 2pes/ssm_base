package com.company.project.module.sys.web;

import cn.hutool.json.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.controller.BaseController;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.sys.model.JobEntity;
import com.company.project.module.sys.service.JobService;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author Chen
 * @created 2019-10-26-22:44.
 */
@RestController
@RequestMapping("/module/sys/job")
public class JobController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private Scheduler quartzScheduler;

    @Autowired
    private JobService jobService;

    @GetMapping()
    @ApiOperation(value = "请求地址", notes = "任务列表地址")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/module/sys/job/list");
        return modelAndView;
    }

    /**
     * 定时列表页
     *
     * @return
     * @throws SchedulerException
     */
    @PostMapping(value = "/list")
    public Result list(QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.getSchedulerJobInfo());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取任务列表失败", e);
            return ResultGenerator.genFailResult("获取任务列表失败！");
        }
    }

    /**
     * 跳转到新增
     *
     * @return
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/toAdd")
    public String toAdd(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {
        return "module/sys/job/addjob";
    }


    /**
     * 新增job
     *
     * @return
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, HttpServletResponse response) throws SchedulerException, ClassNotFoundException {
        String jobName = request.getParameter("jobName");
        String jobGroupName = request.getParameter("jobGroupName");
        String triggerName = request.getParameter("triggerName");
        String triggerGroupName = request.getParameter("triggerGroupName");
        String clazz = request.getParameter("clazz");
        Class cls = Class.forName(clazz);
        String cron = request.getParameter("cron");
        jobService.addJob(jobName, jobGroupName, triggerName, triggerGroupName, cls, cron);
        request.setAttribute("message", "添加任务成功!");
        request.setAttribute("opName", "添加任务!");
        return "module/sys/job/message";
    }

    /**
     * 跳转到编辑
     *
     * @return
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/toEdit")
    public String toEdit(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {

        String jobName = request.getParameter("jobName");
        String jobGroup = request.getParameter("jobGroup");

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        JobDetail jd = quartzScheduler.getJobDetail(jobKey);
        @SuppressWarnings("unchecked")
        List<CronTrigger> triggers = (List<CronTrigger>) quartzScheduler
                .getTriggersOfJob(jobKey);
        CronTrigger trigger = triggers.get(0);
        TriggerKey triggerKey = trigger.getKey();
        String cron = trigger.getCronExpression();
        Map<String, String> pd = new HashMap<String, String>();
        pd.put("jobName", jobKey.getName());
        pd.put("jobGroup", jobKey.getGroup());
        pd.put("triggerName", triggerKey.getName());
        pd.put("triggerGroupName", triggerKey.getGroup());
        pd.put("cron", cron);
        pd.put("clazz", jd.getJobClass().getCanonicalName());

        request.setAttribute("pd", pd);
        request.setAttribute("msg", "edit");

        return "module/sys/job/editjob";
    }


    /**
     * 编辑job
     *
     * @return
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest request, HttpServletResponse response) throws SchedulerException, ClassNotFoundException {
        String jobName = request.getParameter("jobName");
        String jobGroupName = request.getParameter("jobGroupName");
        String triggerName = request.getParameter("triggerName");
        String triggerGroupName = request.getParameter("triggerGroupName");
        String clazz = request.getParameter("clazz");
        Class cls = Class.forName(clazz);
        String cron = request.getParameter("cron");

        String oldjobName = request.getParameter("oldjobName");
        String oldjobGroup = request.getParameter("oldjobGroup");
        String oldtriggerName = request.getParameter("oldtriggerName");
        String oldtriggerGroup = request.getParameter("oldtriggerGroup");

        boolean result = jobService.modifyJobTime(oldjobName, oldjobGroup, oldtriggerName, oldtriggerGroup,
                jobName, jobGroupName, triggerName, triggerGroupName, cron);
        if (result) {
            request.setAttribute("message", "修改任务成功!");
        } else {
            request.setAttribute("message", "修改任务失败!");
        }
        request.setAttribute("opName", "更新任务!");
        return "module/sys/job/message";
    }

    @RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
    @ResponseBody
    public String pauseJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroupName") String jobGroupName) {
        JSONObject json = new JSONObject();

        if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(jobGroupName)) {
            json.put("status", "wrong");
        } else {
            jobService.pauseJob(jobName, jobGroupName);
            json.put("status", "success");
        }

        return json.toString();
    }

    @RequestMapping(value = "/resumeJob", method = RequestMethod.POST)
    @ResponseBody
    public String resumeJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroupName") String jobGroupName) {
        JSONObject json = new JSONObject();

        if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(jobGroupName)) {
            json.put("status", "wrong");
        } else {
            jobService.resumeJob(jobName, jobGroupName);
            json.put("status", "success");
        }

        return json.toString();
    }

    @RequestMapping(value = "/deleteJob", method = RequestMethod.POST)
    @ResponseBody
    public String deleteJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroupName") String jobGroupName,
                            @RequestParam("triggerName") String triggerName, @RequestParam("triggerGroupName") String triggerGroupName) {
        JSONObject json = new JSONObject();

        if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(jobGroupName) ||
                StringUtils.isEmpty(triggerName) || StringUtils.isEmpty(triggerGroupName)) {
            json.put("status", "wrong");
        } else {
            jobService.removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
            json.put("status", "success");
        }

        return json.toString();
    }


    private List<JobEntity> getSchedulerJobInfo() {
        List<JobEntity> jobInfos = new ArrayList<JobEntity>();
        try {
            List<String> triggerGroupNames = quartzScheduler.getTriggerGroupNames();
            for (String triggerGroupName : triggerGroupNames) {
                Set<TriggerKey> triggerKeySet = quartzScheduler
                        .getTriggerKeys(GroupMatcher
                                .triggerGroupEquals(triggerGroupName));
                for (TriggerKey triggerKey : triggerKeySet) {
                    Trigger t = quartzScheduler.getTrigger(triggerKey);
                    if (t instanceof CronTrigger) {
                        CronTrigger trigger = (CronTrigger) t;
                        JobKey jobKey = trigger.getJobKey();
                        JobDetail jd = quartzScheduler.getJobDetail(jobKey);
                        JobEntity jobInfo = new JobEntity();
                        jobInfo.setJobName(jobKey.getName());
                        jobInfo.setJobGroup(jobKey.getGroup());
                        jobInfo.setTriggerName(triggerKey.getName());
                        jobInfo.setTriggerGroupName(triggerKey.getGroup());
                        jobInfo.setCronExpr(trigger.getCronExpression());
                        jobInfo.setNextFireTime(trigger.getNextFireTime());
                        jobInfo.setPreviousFireTime(trigger.getPreviousFireTime());
                        jobInfo.setStartTime(trigger.getStartTime());
                        jobInfo.setEndTime(trigger.getEndTime());
                        jobInfo.setJobClass(jd.getJobClass().getCanonicalName());
                        // jobInfo.setDuration(Long.parseLong(jd.getDescription()));
                        Trigger.TriggerState triggerState = quartzScheduler
                                .getTriggerState(trigger.getKey());
                        jobInfo.setJobStatus(triggerState.toString());// NONE无,
                        // NORMAL正常,
                        // PAUSED暂停,
                        // COMPLETE完全,
                        // ERROR错误,
                        // BLOCKED阻塞
                        JobDataMap map = quartzScheduler.getJobDetail(jobKey)
                                .getJobDataMap();
                        if (null != map && map.size() != 0) {
                            jobInfo.setCount(Integer.parseInt((String) map
                                    .get("count")));
                            jobInfo.setJobDataMap(map);
                        } else {
                            jobInfo.setJobDataMap(new JobDataMap());
                        }
                        jobInfos.add(jobInfo);
                    }
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return jobInfos;
    }

}
