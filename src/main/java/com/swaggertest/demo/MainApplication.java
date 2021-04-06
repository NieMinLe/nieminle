package com.swaggertest.demo;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

public class MainApplication {

    public static void main(String[] args){
        DocsConfig config = new DocsConfig();
        config.setProjectPath("F:\\自动"); // 项目根目录
        config.setProjectName("自动"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("F:\\也是自动"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成

        Docs.buildHtmlDocs(config); // 执行生成文档

    }

}
