package com.utku.taskexecutor.service;

import com.utku.taskexecutor.repository.TaskExecutorRepository;
import com.utku.taskexecutor.service.model.TaskAverageExecutionTimeResult;
import com.utku.taskexecutor.service.model.TaskExecutionResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * DefaultTaskExecutorServiceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultTaskExecutorServiceTest {

    @InjectMocks
    private DefaultTaskExecutorService service;

    @Mock
    private TaskExecutorRepository taskExecutorRepository;

    @Test
    public void shouldRunAndInsertResultForShort(){
        TaskExecutionResult result = service.execute("short");

        assertThat(result.taskId(), equalTo("short"));
        assertThat(result.result(), equalTo("ok"));
        assertThat(result.executionTime(), greaterThan(0L));

        verify(taskExecutorRepository).insertTaskExecutionResult(eq("short"), eq("ok"), anyLong());
    }

    @Test
    public void shouldRunAndInsertResultForLong(){
        TaskExecutionResult result = service.execute("long");

        assertThat(result.taskId(), equalTo("long"));
        assertThat(result.result(), equalTo("ok"));
        assertThat(result.executionTime(), greaterThan(0L));

        verify(taskExecutorRepository).insertTaskExecutionResult(eq("long"), eq("ok"), anyLong());
    }

    @Test
    public void shouldRunAndInsertResultForAlwaysError(){
        TaskExecutionResult result = service.execute("always-error");

        assertThat(result.taskId(), equalTo("always-error"));
        assertThat(result.result(), equalTo("error"));
        assertThat(result.executionTime(), greaterThan(0L));

        verify(taskExecutorRepository).insertTaskExecutionResult(eq("always-error"), eq("error"), anyLong());
    }

    @Test
    public void shouldCalculateAverageTime(){
        when(taskExecutorRepository.calculateAverageExecutionTimeFor("always-error")).thenReturn(10L);

        TaskAverageExecutionTimeResult average = service.calculateAverageTimeFor("always-error");

        assertThat(average.taskId(), equalTo("always-error"));
        assertThat(average.averageExecutionTime(), equalTo(10L));
    }

}