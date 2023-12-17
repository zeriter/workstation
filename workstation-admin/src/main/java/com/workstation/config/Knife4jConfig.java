package com.workstation.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 接口文档配置类
 * @date 2023/12/2 19:11 周六
 */
@Configuration
public class Knife4jConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                // 接口文档标题
                .info(new Info()
                        .title("workstation在线文档")
                        .description("workstation在线文档")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("ZERITER-ZHANG")
                                .email("791825185@@qq.com"))
                        .termsOfService("https://github.com/zeriter/workstation/blob/main/LICENSE")
                        .license(new License()
                                .name("MIT")
                                .url("https://github.com/zeriter/workstation/blob/main/LICENSE")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github 地址").url("https://github.com/zeriter/workstation.git"))
                .components(new Components()
                        .addSecuritySchemes(HttpHeaders.AUTHORIZATION,
                                new SecurityScheme()
                                        .name(HttpHeaders.AUTHORIZATION)
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                        ));
    }
}
