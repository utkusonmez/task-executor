package com.utku.taskexecutor.service;

import com.utku.taskexecutor.service.model.TaskAverageExecutionTimeResult;
import com.utku.taskexecutor.service.model.TaskExecutionResult;

/**
 * TaskExecutorService
 */
public interface TaskExecutorService {

    TaskExecutionResult execute(String taskId);

    TaskAverageExecutionTimeResult calculateAverageTimeFor(String taskId);

}
