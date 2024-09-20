package com.workstation.modules.quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 定时任务配置类
 * @date 2024/2/29 10:18 星期四
 **/
@Configuration
public class QuartzConfig {
    /**
     * 解决Job中注入Spring Bean为null的问题
     */
    @Component
    public static class QuartzJobFactory extends AdaptableJobFactory{
        private final AutowireCapableBeanFactory capableBeanFactory;

        public QuartzJobFactory(AutowireCapableBeanFactory capableBeanFactory) {
            this.capableBeanFactory = capableBeanFactory;
        }

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            // 调用父类的方法
            Object jobInstance = super.createJobInstance(bundle);
            capableBeanFactory.autowireBean(jobInstance);
            return jobInstance;
        }
    }
}
