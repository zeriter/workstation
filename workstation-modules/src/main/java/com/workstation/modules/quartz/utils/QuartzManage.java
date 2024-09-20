package com.workstation.modules.quartz.utils;

import com.workstation.common.exception.BusinessException;
import com.workstation.modules.quartz.domain.entity.QuartzJob;
import jakarta.annotation.Resource;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description @TODO
 * @date 2024/2/29 10:29 星期四
 **/
@Component
public class QuartzManage {
    private static final Logger log = LoggerFactory.getLogger(QuartzManage.class);
    private static final String JOB_NAME = "TASK_";
    @Resource
    private Scheduler scheduler;

    public void addJob(QuartzJob quartzJob) {
        try {
            // 构建job信息
            JobDetail jobDetail =
                    JobBuilder.newJob(ExecutionJob.class).withIdentity(JOB_NAME + quartzJob.getId()).build();

            // 通过触发器名和cron 表达式创建 Trigger
            Trigger cronTrigger = newTrigger()
                    .withIdentity(JOB_NAME + quartzJob.getId())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))
                    .build();

            cronTrigger.getJobDataMap().put(QuartzJob.JOB_KEY, quartzJob);

            // 重置启动时间
            ((CronTriggerImpl) cronTrigger).setStartTime(new Date());

            // 执行定时任务
            scheduler.scheduleJob(jobDetail, cronTrigger);

            // 暂停任务
            if (quartzJob.getIsPause()) {
                pauseJob(quartzJob);
            }
        } catch (Exception e) {
            log.error("创建定时任务失败", e);
            throw new BusinessException("创建定时任务失败");
        }
    }
    public void pauseJob(QuartzJob quartzJob) {
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务暂停失败", e);
            throw new BusinessException("定时任务暂停失败");
        }
    }
}
