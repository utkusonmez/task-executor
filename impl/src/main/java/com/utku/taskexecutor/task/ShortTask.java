package com.utku.taskexecutor.task;

import com.utku.taskexecutor.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ShortTask
 */
public class ShortTask implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortTask.class);

    private static final String TASK_ID = "short";
    public static TaskMatcher MATCHER = new TaskMatcher((TASK_ID::equals), (ShortTask::new));

    @Override
    public String execute() {
        LOGGER.info("short task running");

        Util.INSTANCE.sleepRandomBetween(1, 20);

        return "ok";
    }

}
