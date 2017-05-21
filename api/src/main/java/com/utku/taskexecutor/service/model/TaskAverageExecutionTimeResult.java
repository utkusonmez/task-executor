package com.utku.taskexecutor.service.model;

/**
 * TaskAverageExecutionTimeResult
 */
public class TaskAverageExecutionTimeResult {

    private final String taskId;
    private final long averageExecutionTime;

    public TaskAverageExecutionTimeResult(String taskId, long averageExecutionTime) {
        this.taskId = taskId;
        this.averageExecutionTime = averageExecutionTime;
    }

    public String taskId() {
        return taskId;
    }

    public long averageExecutionTime() {
        return averageExecutionTime;
    }
}
