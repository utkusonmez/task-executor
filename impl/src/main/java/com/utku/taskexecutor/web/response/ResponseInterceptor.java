package com.utku.taskexecutor.web.response;

import com.utku.taskexecutor.TaskExecutorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;

/**
 * ResponseInterceptor
 */
@javax.ws.rs.ext.Provider
public class ResponseInterceptor implements WriterInterceptor, ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseInterceptor.class);

    @Context
    private Provider<ContainerRequestContext> requestContextProvider;

    public void aroundWriteTo(WriterInterceptorContext writerInterceptorContext) throws IOException, WebApplicationException {
        final ContainerRequestContext context = requestContextProvider.get();
        final Object entity = writerInterceptorContext.getEntity();

        if (entity instanceof TaskResponse) {
            writerInterceptorContext.setGenericType(TaskResponse.class);
            writerInterceptorContext.setType(TaskResponse.class);
            writerInterceptorContext.setEntity(responseToEntity(context, (TaskResponse) entity));
        }

        writerInterceptorContext.proceed();
    }

    public Response toResponse(Exception ex) {
        ContainerRequestContext containerRequestContext = requestContextProvider.get();

        LOGGER.error("An exception occured", ex);

        TaskExecutorException te = toTaskExecutorException(ex);

        return Response
                .status(te.status())
                .entity(exceptionToEntity(containerRequestContext, te))
                .build();
    }

    private String getPath(ContainerRequestContext context) {
        return context.getUriInfo().getPath();
    }

    private TaskResponse<?> responseToEntity(ContainerRequestContext containerRequestContext, TaskResponse entity) {
        entity.call(getPath(containerRequestContext));
        return entity;
    }

    private TaskResponse<String> exceptionToEntity(ContainerRequestContext containerRequestContext, TaskExecutorException te) {

        return TaskResponse.<String>builder()
                .call(getPath(containerRequestContext))
                .code(te.code())
                .description(te.message())
                .data("error")
                .build();

    }

    private TaskExecutorException toTaskExecutorException(Exception ex) {
        if (ex instanceof TaskExecutorException) {
            return (TaskExecutorException) ex;
        }

        return TaskExecutorException.UNKNOWN_EXCEPTION(ex);
    }

}
