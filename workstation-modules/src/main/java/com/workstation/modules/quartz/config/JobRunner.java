package com.workstation.modules.quartz.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.workstation.modules.quartz.domain.entity.QuartzJob;
import com.workstation.modules.quartz.mapper.QuartzJobMapper;
import com.workstation.modules.quartz.utils.QuartzManage;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description JobRunner
 * @date 2024/2/29 10:26 星期四
 **/
@Component
public class JobRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(JobRunner.class);
    @Resource
    private QuartzJobMapper quartzJobMapper;
    @Resource
    private QuartzManage quartzManage;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("--------------------注入定时任务---------------------");
        List<QuartzJob> quartzJobs = quartzJobMapper.selectList(new LambdaQueryWrapper<QuartzJob>()
                .eq(QuartzJob::getIsPause, 0));
        quartzJobs.forEach(quartzManage::addJob);
        log.info("--------------------定时任务注入完成---------------------");
    }
}
