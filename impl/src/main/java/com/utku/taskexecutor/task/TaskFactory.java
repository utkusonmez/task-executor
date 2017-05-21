package com.utku.taskexecutor.task;

import com.utku.taskexecutor.TaskExecutorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TaskFactory
 * Spring handles singleton objects better but i want to show Factory and singleton pattern
 */
public class TaskFactory {

    private static TaskFactory taskFactory = null;

    private List<TaskMatcher> taskMatchers = new ArrayList<>();

    private TaskFactory() {
        taskMatchers.add(ShortTask.MATCHER);
        taskMatchers.add(LongTask.MATCHER);
        taskMatchers.add(AlwaysErrorTask.MATCHER);
    }

    public static synchronized TaskFactory getInstance() {
        if (taskFactory == null) {
            taskFactory = new TaskFactory();
        }

        return taskFactory;
    }

    public Task getTask(String taskId) {
        Optional<TaskMatcher> matcher = taskMatchers.stream()
                .filter(m -> m.matches(taskId))
                .findFirst();

        if (matcher.isPresent()) {
            return matcher.get().generate();
        }

        throw TaskExecutorException.TASK_NOT_FOUND;
    }

}
