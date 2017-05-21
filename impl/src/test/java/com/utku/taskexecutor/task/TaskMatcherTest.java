package com.utku.taskexecutor.task;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

/**
 * TaskMatcherTest
 */
public class TaskMatcherTest {


    @Test
    public void shouldMatchAndGenerateLongTask(){

        TaskMatcher matcher = LongTask.MATCHER;

        assertThat(matcher.matches("long"), equalTo(true));
        assertThat(matcher.matches("a"), equalTo(false));

        assertThat(matcher.generate(), instanceOf(LongTask.class));
    }

}