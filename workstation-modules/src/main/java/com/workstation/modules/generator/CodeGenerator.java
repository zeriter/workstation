package com.workstation.modules.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 代码生成
 * @date 2024-12-17 09:36 星期二
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        String moduleName = "test";
        String tableName = "test";
        FastAutoGenerator generator = FastAutoGenerator.create("jdbc:mysql://localhost:3306/workstation?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&autoReconnect=true&allowMultiQueries=true", "root", "root@123")
                // 全局配置
                .globalConfig((scanner, builder) -> builder
                        .author("ZERITER-ZHANG")
                        .disableOpenDir()
                        .commentDate("yyyy-MM-dd hh:mm:ss")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/workstation-modules/src/main/java")
                )
                // 包配置
                .packageConfig((scanner, builder) -> builder
                        .parent("com.workstation.modules")
                        .moduleName(moduleName)
                        .entity("domain.entity")
                        .controller("controller")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/workstation-modules/src/main/resources/mapper"))

                )
                // 策略配置
                .strategyConfig(builder -> builder
                        .enableSkipView()
                        .addTablePrefix("sys_")
                        .addInclude(getTables(tableName))
                        .entityBuilder()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.UPDATE)
                        )
                        .build())
                .injectionConfig(builder -> {
                    List<CustomFile> customFiles = new ArrayList<>();
                    customFiles.add(new CustomFile.Builder().packageName("domain.form").fileName("Form.java").templatePath("vm/form.java.vm").build());
                    customFiles.add(new CustomFile.Builder().packageName("domain.query").fileName("PageQuery.java").templatePath("vm/query.java.vm").build());
                    customFiles.add(new CustomFile.Builder().packageName("domain.result").fileName("PageResult.java").templatePath("vm/result.java.vm").build());
                    customFiles.add(new CustomFile.Builder().packageName("converter").fileName("Converter.java").templatePath("vm/converter.java.vm").build());
                    customFiles.add(new CustomFile.Builder().packageName("vue").fileName("index.vue").templatePath("vm/index.vue.vm").build());
                    customFiles.add(new CustomFile.Builder().packageName("vue").fileName("Types.ts").templatePath("vm/types.ts.vm").build());
                    customFiles.add(new CustomFile.Builder().packageName("vue").fileName("Index.ts").templatePath("vm/index.ts.vm").build());

                    builder.customFile(customFiles).build();

                })
                .templateEngine(new VelocityTemplateEngine())
                .templateConfig(builder -> builder
                        .controller("vm/controller.java.vm")
                        .service("vm/service.java.vm")
                        .serviceImpl("vm/serviceImpl.java.vm")
                        .mapper("vm/mapper.java.vm")
                        .entity("vm/entity.java.vm")
                        .xml("vm/mapper.xml.vm")
                );
        generator.execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
