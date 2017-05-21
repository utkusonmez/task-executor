package com.utku.taskexecutor.task;

import com.utku.taskexecutor.TaskExecutorException;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * TaskFactoryTest
 */
public class TaskFactoryTest {

    @Test
    public void shouldBuildShortTask() {
        Task task = TaskFactory.getInstance().getTask("short");
        assertNotNull(task);
    }

    @Test
    public void shouldBuildLongTask() {
        Task task = TaskFactory.getInstance().getTask("long");
        assertNotNull(task);
    }

    @Test
    public void shouldBuildAlwaysError() {
        Task task = TaskFactory.getInstance().getTask("always-error");
        assertNotNull(task);
    }

    @Test
    public void shouldThrowExceptionWhenNoMatchersFound() {

        try {
            TaskFactory.getInstance().getTask("unknown");
            fail();
        } catch (TaskExecutorException e) {
            assertThat(e.code(), equalTo("EX-TASK-005"));
        }

    }

}