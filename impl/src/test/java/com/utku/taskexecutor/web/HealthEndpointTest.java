package com.utku.taskexecutor.web;

import com.utku.taskexecutor.web.response.TaskResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * HealthEndpointTest
 */
@RunWith(MockitoJUnitRunner.class)
public class HealthEndpointTest {

    @InjectMocks
    private HealthEndpoint healthEndpoint;

    @Test
    public void shouldReturnUp() {
        TaskResponse<String> health = healthEndpoint.health();

        assertThat(health.code(), equalTo("OK-HEALTH-001"));
        assertThat(health.description(), equalTo("App health check"));
        assertThat(health.data(), equalTo("up"));
    }

}