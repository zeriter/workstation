package com.workstation.modules.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description @TODO
 * @date 2024/3/1 10:03 星期五
 **/
@Component
public class HelloTask {
    private static final Logger log = LoggerFactory.getLogger(HelloTask.class);

    public void helloWord(String params) {
        log.info("run " + new Date().getTime());
    }
}
