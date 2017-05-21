package com.utku.taskexecutor.task;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * LongTaskTest
 */
public class LongTaskTest {

    @Test
    public void shouldRun(){

        String result = new LongTask().execute();

        assertThat(result, equalTo("ok"));

    }

}