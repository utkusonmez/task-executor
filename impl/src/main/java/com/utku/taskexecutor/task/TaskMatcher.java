package com.utku.taskexecutor.task;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * TaskMatcher
 */
class TaskMatcher {

    private final Function<String, Boolean> matcher;
    private final Supplier<Task> generator;

    TaskMatcher(Function<String, Boolean> matcher, Supplier<Task> generator) {
        this.matcher = matcher;
        this.generator = generator;
    }

    boolean matches(String taskId) {
        return matcher.apply(taskId);
    }

    Task generate() {
        return generator.get();
    }
}
