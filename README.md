#项目自述
- 技术：
- mybatis、tk.mybatis、swagger2、lombok、fastJson
- 自定义exception、commons.lang3校验、PageHelper
- thymeleaf页面、
## 注意事项
- [斗鱼](https://www.douyu.com/directory/all)
- [虎牙](https://www.huya.com/l)

## 部署情况
部署环境|机器IP|是否上云
:---:|:---:|:---:
联调环境|172.16.68.232|否
测试环境|192.168.1.34|是
线上环境|192.168.50.100|是

## 目录介绍
```bash
├─swaggerdemo                               # 项目名称
│  ├─src                                           # 项目源文件
│  │  ├─main
│  │  │  ├─java                                    # java源文件
│  │  │  │  └─com
│  │  │  │      └─swaggertest
│  │  │  │          └─demo
│  │  │  │                  ├─aop                  # 切面
│  │  │  │                  ├─config               # config
│  │  │  │                  ├─controller           # controller层
│  │  │  │                  ├─dao                  # mapper层
│  │  │  │                  ├─domain               # 实体层
│  │  │  │                  │  ├─dao              
│  │  │  │                  │  ├─po              
│  │  │  │                  │─exception            # 异常类
│  │  │  │                  │─interceptor          # 拦截器
│  │  │  │                  │─param                # 参数类型
│  │  │  │                  │─service              # 服务层
│  │  │  │                  │─servlet              # 小服务
│  │  │  │                  │─system               # 常量和枚举
│  │  │  │                  └─utils                # 工具类
│  │  │  └─resources                                # 项目资源文件  
│  │  │  │  ├── mapper                            # mybatis文件
│  │  │  │  ├── templates                         # html
│  │  │  │  └─— application.properties            # 项目启动配置文件
│  │  │  ├── /test                                 # 所有自测试用例    
│  │  ├── /target                                  # 编译后的输出目录 
│  │  ├── pom.xml                                  # tanzk-data-collect 服务提供模块相关依赖配置
```










