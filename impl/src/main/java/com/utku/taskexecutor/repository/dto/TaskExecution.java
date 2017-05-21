package com.utku.taskexecutor.repository.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * TaskExecution
 */
@Document(collection = "task_execution")
public class TaskExecution {

    @Id
    private String id;
    private String taskId;
    private String result;
    private Long elapsedTime;

    public TaskExecution(String id, String taskId, String result, Long elapsedTime) {
        this.id = id;
        this.taskId = taskId;
        this.result = result;
        this.elapsedTime = elapsedTime;
    }

    public TaskExecution(String taskId, String result, Long elapsedTime) {
        this.taskId = taskId;
        this.result = result;
        this.elapsedTime = elapsedTime;
    }

    public TaskExecution() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
