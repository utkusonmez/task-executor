package com.utku.taskexecutor.repository;

/**
 * TaskExecutorRepository
 */
public interface TaskExecutorRepository {

    String insertTaskExecutionResult(String taskId, String result, Long executionTime);

    long calculateAverageExecutionTimeFor(String taskId);

}
