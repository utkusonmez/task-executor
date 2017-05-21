package com.utku.taskexecutor.task;

import com.utku.taskexecutor.TaskExecutorException;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * AlwaysErrorTaskTest
 */
public class AlwaysErrorTaskTest {

    @Test
    public void shouldFail(){

        try {
            new AlwaysErrorTask().execute();
            fail();
        } catch (TaskExecutorException e) {
            assertThat(e.code(), equalTo("EX-TASK-004"));
        }

    }

}