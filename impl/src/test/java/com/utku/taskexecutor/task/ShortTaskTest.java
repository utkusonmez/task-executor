package com.utku.taskexecutor.task;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * ShortTaskTest
 */
public class ShortTaskTest {

    @Test
    public void shouldRun(){

        String result = new ShortTask().execute();

        assertThat(result, equalTo("ok"));

    }

}