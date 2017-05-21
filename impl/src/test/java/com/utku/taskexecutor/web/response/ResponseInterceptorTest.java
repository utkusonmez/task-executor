package com.utku.taskexecutor.web.response;

import com.utku.taskexecutor.TaskExecutorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Provider;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.WriterInterceptorContext;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * ResponseInterceptorTest
 */
@RunWith(MockitoJUnitRunner.class)
public class ResponseInterceptorTest {

    @InjectMocks
    private ResponseInterceptor responseInterceptor;

    @Mock
    private Provider<ContainerRequestContext> requestContextProvider;

    @Mock
    private WriterInterceptorContext writerInterceptorContext;

    @Captor
    private ArgumentCaptor<TaskResponse<String>> captor;

    @Test
    public void shouldAddPathToResponse() throws IOException {

        buildCall();

        TaskResponse<String> taskResponse = new TaskResponse<>("", "code-123", "", "");
        when(writerInterceptorContext.getEntity()).thenReturn(taskResponse);

        responseInterceptor.aroundWriteTo(writerInterceptorContext);

        verify(writerInterceptorContext).proceed();
        verify(writerInterceptorContext).setType(TaskResponse.class);
        verify(writerInterceptorContext).setGenericType(TaskResponse.class);

        verify(writerInterceptorContext).setEntity(captor.capture());
        TaskResponse<String> value = captor.getValue();
        assertThat(value.call(), equalTo("/call"));
        assertThat(value.call(), equalTo("/call"));
        assertThat(value.code(), equalTo("code-123"));
    }

    @Test
    public void shouldRunForResponseWhenResponseIsNotTaskResponse() throws IOException {

        buildCall();

        when(writerInterceptorContext.getEntity()).thenReturn("HELLO");

        responseInterceptor.aroundWriteTo(writerInterceptorContext);

        verify(writerInterceptorContext).proceed();
    }

    @Test
    public void shouldBuildTaskResponseFromTaskException(){

        buildCall();

        TaskExecutorException te = TaskExecutorException.CUSTOM(303, "hello", "exception");

        Response response = responseInterceptor.toResponse(te);

        assertThat(response.getStatus(), equalTo(303));
        assertThat(((TaskResponse) response.getEntity()).call(), equalTo("/call"));
        assertThat(((TaskResponse) response.getEntity()).description(), equalTo("exception"));
    }

    @Test
    public void shouldBuildTaskResponseFromAnyException(){

        buildCall();

        Response response = responseInterceptor.toResponse(new RuntimeException());

        assertThat(response.getStatus(), equalTo(500));
        assertThat(((TaskResponse) response.getEntity()).call(), equalTo("/call"));
        assertThat(((TaskResponse) response.getEntity()).description(), equalTo("Unknown exception is occured."));
    }

    private void buildCall() {
        UriInfo uriInfo = mock(UriInfo.class);
        when(uriInfo.getPath()).thenReturn("/call");

        ContainerRequestContext request = mock(ContainerRequestContext.class);
        when(request.getUriInfo()).thenReturn(uriInfo);
        when(requestContextProvider.get()).thenReturn(request);
    }

}