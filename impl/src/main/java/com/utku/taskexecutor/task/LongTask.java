package com.utku.taskexecutor.task;

import com.utku.taskexecutor.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LongTask
 */
public class LongTask implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(LongTask.class);

    private static final String TASK_ID = "long";
    public static TaskMatcher MATCHER = new TaskMatcher((TASK_ID::equals), (LongTask::new));

    @Override
    public String execute() {
        LOGGER.info("long task running");

        Util.INSTANCE.sleepRandomBetween(30, 50);

        return "ok";
    }
}
