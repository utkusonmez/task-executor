package com.utku.taskexecutor.repository;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.utku.taskexecutor.repository.dto.TaskAverageTime;
import com.utku.taskexecutor.repository.dto.TaskExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * DefaultTaskExecutorRepository
 */
public class DefaultTaskExecutorRepository implements TaskExecutorRepository {

    private MongoTemplate mongoTemplate;

    public DefaultTaskExecutorRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String insertTaskExecutionResult(String taskId, String result, Long executionTime) {

        TaskExecution taskExecution = new TaskExecution(taskId, result, executionTime);
        mongoTemplate.save(taskExecution);
        return taskExecution.getId();
    }

    @Override
    public long calculateAverageExecutionTimeFor(String taskId) {

        GroupOperation group = group().avg("elapsedTime").as("avg");

        MatchOperation match = match(new Criteria("taskId").is(taskId));

        ProjectionOperation projection = project("avg");

        Aggregation aggregation = newAggregation(match, group, projection);

        AggregationResults<TaskAverageTime> result = mongoTemplate.aggregate(aggregation, TaskExecution.class, TaskAverageTime.class);
        return result.getUniqueMappedResult().getAvg().longValue();
    }
}
