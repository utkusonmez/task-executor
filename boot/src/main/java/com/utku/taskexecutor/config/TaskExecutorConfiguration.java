package com.utku.taskexecutor.config;

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
    public ResourceConfig resourceConfig(){
        final ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(GsonFeature.class);
        resourceConfig.packages("com.utku.taskexecutor.web");
        return resourceConfig;
    }

}
