package com.workstation.modules.quartz.utils;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.workstation.modules.quartz.domain.entity.QuartzJob;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description @TODO
 * @date 2024/2/29 11:37 星期四
 **/
@Async
public class ExecutionJob extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(ExecutionJob.class);
    private final static ThreadPoolExecutor EXECUTOR = ThreadPoolExecutorUtil.getPoll();
    @Override
    public void executeInternal(JobExecutionContext context) {
        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        String uuid = quartzJob.getUuid();
        long startTime = System.currentTimeMillis();
        try {
            // 执行任务
            log.info("--------------------------------------------------------------");
            log.info("任务开始执行，任务名称：" + quartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
            Future<?> future = EXECUTOR.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            // 任务状态
            log.info("任务执行完毕，任务名称：" + quartzJob.getJobName() + ", 执行时间：" + times + "毫秒");
            log.info("--------------------------------------------------------------");
            // 判断是否存在子任务
            if (StringUtils.isNotBlank(quartzJob.getSubTask())) {
                String[] tasks = quartzJob.getSubTask().split("[,，]");
                // @TODO执行子任务
            }
        } catch (Exception e) {
            System.out.println("任务执行失败，任务名称：" + quartzJob.getJobName());
            System.out.println("--------------------------------------------------------------");
            long times = System.currentTimeMillis() - startTime;
            // 任务如果失败了则暂停
            if (quartzJob.getPauseAfterFailure() != null && quartzJob.getPauseAfterFailure()) {
                quartzJob.setIsPause(false);
                // @TODO更新状态
            }
            if (quartzJob.getEmail() != null) {
                // TODO邮箱报警
            }
        } finally {
            // @TODO
        }
    }

}
