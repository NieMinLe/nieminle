#项目自述
- 技术：
- mybatis、tk.mybatis、swagger2、lombok、fastJson
- 自定义exception、commons.lang3校验、PageHelper

## 注意事项
- [斗鱼](https://www.douyu.com/directory/all)


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
│  │  │  │                  ├─controller           # controller层
│  │  │  │                  ├─dao                  # mapper层
│  │  │  │                  ├─entity               # 实体层
│  │  │  │                  │  ├─dao              
│  │  │  │                  │─Exception            # 异常类
│  │  │  │                  │─param                # 参数类型
│  │  │  │                  │─service              # 服务层
│  │  │  │                  └─swagger              # swagger配置
│  │  │  └─resources                               # 项目资源文件  
│  │  │  │  ├── application.properties             # 项目启动配置文件
│  │  │  ├── /test                                 # 所有自测试用例    
│  │  ├── /target                                  # 编译后的输出目录 
│  │  ├── pom.xml                                  # tanzk-data-collect 服务提供模块相关依赖配置
```










