# WORKSTATION

## 简介

个人工作站快速开发框架workstation项目是基于springboot、jwt、redis、mybatis-plus、vue等技术的前后端分离项目。项目地址：https://github.com/zeriter/workstation
。配套前端：https://github.com/zeriter/workstation-ui

## 技术栈

### 技术介绍

| 技术               | 版本          | 描述      |
|------------------|-------------|---------|
| jdk              | 17          | 开发工具    |
| mysql            | 8           | 数据库支持   |
| spring-boot      | 3.1.5       | 开发基础环境  |
| fastjson2        | 2.0.41      | json框架  |
| hutool-all       | 5.8.16      | 常用工具包   |
| mybatis-plus     | 3.5.3.1     | 数据库链接框架 |
| druid            | 1.2.16      | 数据库连接池  |
| jjwt             | 0.11.5      | 安全认证    |
| mapstruct        | 1.5.5.Final | 对象转化框架  |
| easyexcel        | 3.2.1       | 文档操作框架  |
| knife4j-openapi3 | 4.3.0       | 接口文档框架  |
| 待补充……            | 待补充……       | 待补充……   |

### 结构介绍

项目模块介绍

```shell
┌─workstation
│   ├─workstation-admin     # 项目启动、认证、配置模块
│   ├─workstation-common    # 通用模块
│   ├─workstation-generator # 代码生成模块
└───└─workstation-modules   # 业务开发模块
```

开发目录介绍

```shell
system
  ├─controller    # 接口访问层
  ├─converter     # 对象转换器
  ├─domain        # 对象层
  │  ├─bo         # 逻辑处理对象
  │  ├─entity     # 表格类
  │  ├─form       # 表单操作对象
  │  ├─query      # 查询操作对象
  │  ├─result     # 结果返回对象
  │  └─security   # 安全认证相关
  ├─enums         # 枚举对象
  ├─mapper        # 持久层对象
  ├─plugin        # 组件对象
  │  └─easyexcel  # excel处理对象
  ├─service       # 逻辑处理层
  │  └─impl       # 逻辑处理实现层
  └─utils         # 工具层
```


