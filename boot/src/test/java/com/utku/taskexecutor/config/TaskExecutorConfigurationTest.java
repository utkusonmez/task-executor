package com.utku.taskexecutor.config;

import com.utku.taskexecutor.web.feature.GsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * TaskExecutorConfigurationTest
 */
public class TaskExecutorConfigurationTest {

    @Test
    public void shouldPrepareResourceConfig() {
        ResourceConfig resourceConfig = new TaskExecutorConfiguration().resourceConfig();

        assertThat(resourceConfig.isRegistered(GsonFeature.class), equalTo(true));

        // couldnt check packages :(
    }

}