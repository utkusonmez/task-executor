package com.utku.taskexecutor.repository;

/**
 * TaskExecutorRepository
 */
public interface TaskExecutorRepository {

    boolean insertTaskExecutionResult(String taskId, String result, Long executionTime);

    long calculateAverageExecutionTimeFor(String taskId);

}
