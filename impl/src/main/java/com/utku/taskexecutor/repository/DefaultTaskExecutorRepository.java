package com.utku.taskexecutor.repository;

/**
 * DefaultTaskExecutorRepository
 */
public class DefaultTaskExecutorRepository implements TaskExecutorRepository {

    @Override
    public boolean insertTaskExecutionResult(String taskId, String result, Long executionTime) {
        return false;
    }

    @Override
    public long calculateAverageExecutionTimeFor(String taskId) {
        return 4;
    }
}
