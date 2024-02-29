package com.workstation.modules.quartz.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 测试job任务
 * @date 2024/2/29 9:59 星期四
 **/
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }
}
