package com.utku.taskexecutor.service.model;

/**
 * TaskExecutionResult
 */
public class TaskExecutionResult {

    private final String id;
    private final String taskId;
    private final String result;
    private final long executionTime;

    public TaskExecutionResult(String id, String taskId, String result, long executionTime) {
        this.id = id;
        this.taskId = taskId;
        this.result = result;
        this.executionTime = executionTime;
    }

    public String id() {
        return id;
    }

    public String taskId() {
        return taskId;
    }

    public String result() {
        return result;
    }

    public long executionTime() {
        return executionTime;
    }
}
