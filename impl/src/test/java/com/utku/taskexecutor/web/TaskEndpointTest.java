package com.utku.taskexecutor.web;

import com.utku.taskexecutor.TaskExecutorException;
import com.utku.taskexecutor.service.TaskExecutorService;
import com.utku.taskexecutor.service.model.TaskAverageExecutionTimeResult;
import com.utku.taskexecutor.service.model.TaskExecutionResult;
import com.utku.taskexecutor.web.response.TaskResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * TaskEndpointTest
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskEndpointTest {

    @InjectMocks
    private TaskEndpoint taskEndpoint;

    @Mock
    private TaskExecutorService taskExecutorService;

    @Test
    public void shouldExecuteShortNamedTask() {

        TaskExecutionResult result = new TaskExecutionResult("", "short", "", 2L);
        when(taskExecutorService.execute("short")).thenReturn(result);

        TaskResponse<TaskExecutionResult> shortTask = taskEndpoint.execute("short");

        assertThat(shortTask.code(), equalTo("OK-TASK-EXE-001"));
        assertThat(shortTask.data().taskId(), equalTo("short"));
    }

    @Test
    public void shouldThrowExceptionWhenTaskNameIsEmptyForTaskExecute() {

        try {
            taskEndpoint.execute("");
            fail();
        } catch (TaskExecutorException e) {
            assertThat(e.code(), equalTo("EX-TASK-002"));
        }

    }

    @Test
    public void shouldThrowExceptionWhenTaskNameIsNullForTaskExecute() {

        try {
            taskEndpoint.execute(null);
            fail();
        } catch (TaskExecutorException e) {
            assertThat(e.code(), equalTo("EX-TASK-002"));
        }

    }

    @Test
    public void shouldThrowExceptionWhenTaskNameIsInvalidForTaskExecute() {

        try {
            taskEndpoint.execute("UNKNOWN");
            fail();
        } catch (TaskExecutorException e) {
            assertThat(e.code(), equalTo("EX-TASK-002"));
        }

    }

    @Test
    public void shouldCalculateAverageShortNamedTask() {

        TaskAverageExecutionTimeResult result = new TaskAverageExecutionTimeResult("short", 10L);
        when(taskExecutorService.calculateAverageTimeFor("short")).thenReturn(result);

        TaskResponse<TaskAverageExecutionTimeResult> shortTask = taskEndpoint.calculateAverageExecutionTime("short");

        assertThat(shortTask.code(), equalTo("OK-TASK-EXE-002"));
        assertThat(shortTask.data().taskId(), equalTo("short"));
        assertThat(shortTask.data().averageExecutionTime(), equalTo(10L));
    }

    @Test
    public void shouldThrowExceptionWhenTaskNameIsEmptyForTaskAverage() {

        try {
            taskEndpoint.calculateAverageExecutionTime("");
            fail();
        } catch (TaskExecutorException e) {
            assertThat(e.code(), equalTo("EX-TASK-003"));
        }

    }

    @Test
    public void shouldThrowExceptionWhenTaskNameIsNullForTaskAverage() {

        try {
            taskEndpoint.calculateAverageExecutionTime(null);
            fail();
        } catch (TaskExecutorException e) {
            assertThat(e.code(), equalTo("EX-TASK-003"));
        }

    }

    @Test
    public void shouldThrowExceptionWhenTaskNameIsInvalidForTaskAverage() {

        try {
            taskEndpoint.calculateAverageExecutionTime("UNKNOWN");
            fail();
        } catch (TaskExecutorException e) {
            assertThat(e.code(), equalTo("EX-TASK-003"));
        }

    }

}