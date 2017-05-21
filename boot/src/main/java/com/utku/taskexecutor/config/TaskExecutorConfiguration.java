package com.utku.taskexecutor.config;

import com.utku.taskexecutor.repository.DefaultTaskExecutorRepository;
import com.utku.taskexecutor.repository.TaskExecutorRepository;
import com.utku.taskexecutor.service.DefaultTaskExecutorService;
import com.utku.taskexecutor.service.TaskExecutorService;
import com.utku.taskexecutor.web.feature.GsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TaskExecutorConfiguration
 */
@Configuration
public class TaskExecutorConfiguration {

    @Bean
    public ResourceConfig resourceConfig() {
        final ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(GsonFeature.class);
        resourceConfig.packages("com.utku.taskexecutor.web");
        return resourceConfig;
    }

    @Bean
    public TaskExecutorService taskExecutorService(TaskExecutorRepository taskExecutorRepository) {
        return new DefaultTaskExecutorService(taskExecutorRepository);
    }

    @Bean
    public TaskExecutorRepository taskExecutorRepository() {
        return new DefaultTaskExecutorRepository();
    }

}
