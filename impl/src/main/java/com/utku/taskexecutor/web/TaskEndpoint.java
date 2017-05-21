package com.utku.taskexecutor.web;

import com.utku.taskexecutor.TaskExecutorException;
import com.utku.taskexecutor.service.TaskExecutorService;
import com.utku.taskexecutor.service.model.TaskAverageExecutionTimeResult;
import com.utku.taskexecutor.service.model.TaskExecutionResult;
import com.utku.taskexecutor.web.response.TaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/task")
public class TaskEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskEndpoint.class);

    @Inject
    private TaskExecutorService taskExecutorService;

    @POST
    @Path("/{task-id}/execute")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TaskResponse<TaskExecutionResult> execute(@PathParam("task-id") String taskId){

        if (StringUtils.isEmpty(taskId) || !taskIdMatches(taskId)) {
            throw TaskExecutorException.INVALID_TASK_EXECUTION_PARAMS;
        }

        LOGGER.info("executing task ({}) ", taskId);

        return TaskResponse.<TaskExecutionResult>builder()
                .code("OK-TASK-EXE-001")
                .description("Task executed.")
                .data(taskExecutorService.execute(taskId))
                .build();
    }

    @POST
    @Path("/{task-id}/calculateAverageExecutionTime")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TaskResponse<TaskAverageExecutionTimeResult> calculateAverageExecutionTime(@PathParam("task-id") String taskId){

        if (StringUtils.isEmpty(taskId) || !taskIdMatches(taskId)) {
            throw TaskExecutorException.INVALID_TASK_AVERAGE_PARAMS;
        }

        LOGGER.info("calculating average execution time for Task ({}) ", taskId);

        return TaskResponse.<TaskAverageExecutionTimeResult>builder()
                .code("OK-TASK-EXE-002")
                .description("Task average calculated.")
                .data(taskExecutorService.calculateAverageTimeFor(taskId))
                .build();
    }

    private boolean taskIdMatches(String taskId){
        List<String> tasks = Arrays.asList("short", "long", "always-error");
        return tasks.contains(taskId);
    }

}
