package com.utku.taskexecutor.repository.dto;

/**
 * TaskAverageTime
 */
public class TaskAverageTime {

    private String id;
    private Double avg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
}
