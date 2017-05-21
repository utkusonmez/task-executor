package com.utku.taskexecutor.task;

import com.utku.taskexecutor.TaskExecutorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AlwaysErrorTask
 */
public class AlwaysErrorTask implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlwaysErrorTask.class);

    private static final String TASK_ID = "always-error";
    public static TaskMatcher MATCHER = new TaskMatcher((TASK_ID::equals), (AlwaysErrorTask::new));

    @Override
    public String execute() {
        LOGGER.info("always error task is running");

        throw TaskExecutorException.ALWAYS_ERROR_FAILED_AGAIN;
    }
}
