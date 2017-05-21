package com.utku.taskexecutor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:context.xml"})
public class TaskExecutorApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder()
                .headless(true)
                .registerShutdownHook(true)
                .logStartupInfo(true)
                .web(true)
                .sources(TaskExecutorApplication.class)
                .run(args);
    }

}