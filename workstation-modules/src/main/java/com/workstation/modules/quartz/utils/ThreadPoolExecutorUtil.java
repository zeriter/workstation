package com.workstation.modules.quartz.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.workstation.modules.quartz.thread.AsyncTaskProperties;
import com.workstation.modules.quartz.thread.TheadFactoryName;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description @TODO
 * @date 2024/2/29 11:41 星期四
 **/
public class ThreadPoolExecutorUtil {

    static {
        // 可以实现子线程获取主线程的上下文信息
        // 存在一个问题，因为重启应用后上下文信息被销毁，需要重复登录
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
    public static ThreadPoolExecutor getPoll() {
        AsyncTaskProperties properties = SpringUtil.getBean(AsyncTaskProperties.class);
        return new ThreadPoolExecutor(
                properties.getCorePoolSize(),
                properties.getMaxPoolSize(),
                properties.getKeepAliveSeconds(),
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(properties.getQueueCapacity()),
                new TheadFactoryName());
    }
}
