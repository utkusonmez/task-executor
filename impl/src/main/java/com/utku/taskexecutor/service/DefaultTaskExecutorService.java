package com.utku.taskexecutor.service;

import com.utku.taskexecutor.repository.TaskExecutorRepository;
import com.utku.taskexecutor.service.model.TaskAverageExecutionTimeResult;
import com.utku.taskexecutor.service.model.TaskExecutionResult;
import com.utku.taskexecutor.task.ShortTask;
import com.utku.taskexecutor.task.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultTaskExecutorService
 */
public class DefaultTaskExecutorService implements TaskExecutorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTaskExecutorService.class);

    private TaskExecutorRepository taskExecutorRepository;

    public DefaultTaskExecutorService(TaskExecutorRepository taskExecutorRepository) {
        this.taskExecutorRepository = taskExecutorRepository;
    }

    @Override
    public TaskExecutionResult execute(String taskId) {

        TaskFactory taskFactory = TaskFactory.getInstance();

        long startTime = System.currentTimeMillis();

        String result = executeAndHandleException(taskFactory, taskId);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        insertExecutionTime(taskId, result, elapsedTime);

        LOGGER.error("Preparing result of {} in {} ms", taskId, elapsedTime);

        return new TaskExecutionResult(taskId, result, elapsedTime);
    }

    @Override
    public TaskAverageExecutionTimeResult calculateAverageTimeFor(String taskId) {
        long averageTime = taskExecutorRepository.calculateAverageExecutionTimeFor(taskId);
        return new TaskAverageExecutionTimeResult(taskId, averageTime);
    }

    private void insertExecutionTime(String taskId, String result, Long elapsedTimeInMilliseconds){
        LOGGER.error("Inserting result of {} in {} ms", taskId, elapsedTimeInMilliseconds);

        taskExecutorRepository.insertTaskExecutionResult(taskId, result, elapsedTimeInMilliseconds);
    }

    private String executeAndHandleException(TaskFactory taskFactory, String taskId) {

        try {
            return taskFactory.getTask(taskId).execute();
        } catch (Exception e) {
            LOGGER.error("Exception occurred during task execution", e);
        }

        return "error";
    }

}
