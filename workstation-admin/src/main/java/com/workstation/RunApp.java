package com.workstation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 启动类
 * @date 2023/12/2 16:56 周六
 */
@SpringBootApplication
public class RunApp {
    private static final Logger logger = LoggerFactory.getLogger(RunApp.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(RunApp.class);
        ConfigurableApplicationContext application = app.run(args);
        Environment env = application.getEnvironment();
        logger.info("----------------------------------------------------------");
        logger.info("\tPid: \t\t{}", new ApplicationPid());
        logger.info("\tLocal: \t\thttp://localhost:{}", env.getProperty("server.port"));
        logger.info("\tNetwork: \thttp://{}:{}", InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
        logger.info("\tDoc: \t\thttp://{}:{}/doc.html", InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
        logger.info("----------------------------------------------------------");
    }
}
