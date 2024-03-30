package com.workstation;

import com.workstation.common.annotation.OpenAuth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 启动类
 * @date 2023/12/2 16:56 周六
 */
@SpringBootApplication
@RestController
@Tag(name = "监控-健康检查")
@MapperScan("com.workstation.**.mapper")
public class RunApp {
    private static final Logger logger = LoggerFactory.getLogger(RunApp.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(RunApp.class);
        app.addListeners(new ApplicationPidFileWriter());
        ConfigurableApplicationContext application = app.run(args);
        Environment env = application.getEnvironment();
        logger.info("----------------------------------------------------------");
        logger.info("\tPid: \t\t{}", new ApplicationPid());
        logger.info("\tLocal: \t\thttp://localhost:{}", env.getProperty("server.port"));
        logger.info("\tNetwork: \thttp://{}:{}", InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
        logger.info("\tDoc: \t\thttp://{}:{}/doc.html", InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
        logger.info("----------------------------------------------------------");
    }

    @OpenAuth
    @GetMapping("/")
    @Operation(summary = "健康检查", description = "健康检查")
    public String health() {
        return "Backend service started successfully";
    }
}
