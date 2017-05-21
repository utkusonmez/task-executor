package com.utku.taskexecutor.web;

import com.utku.taskexecutor.web.response.TaskResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/health")
public class HealthEndpoint {

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TaskResponse<String> health(){

        return TaskResponse.<String>builder()
                .code("OK-HEALTH-001")
                .description("App health check")
                .data("up")
                .build();
    }

}
