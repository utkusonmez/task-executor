package com.utku.taskexecutor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TaskExecutorException
 */
public class TaskExecutorException extends RuntimeException {

    private static final int SC_BAD_REQUEST = 400;
    private static final int SC_NOT_AUTHORIZED = 401;
    private static final int SC_IMPROPER_RESPONSE = 422;
    private static final int SC_METHOD_FAILURE = 424;
    private static final int SC_SERVER_FAILURE = 500;

    private final int status;
    private final String code;

    protected TaskExecutorException(int status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    protected TaskExecutorException(int status, String code, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
    }

    public int status() {
        return status;
    }

    public String code() {
        return code;
    }

    public String message() {
        return getMessage();
    }

    public static TaskExecutorException CUSTOM(int status, String code, String message) {
        return new TaskExecutorException(status, code, message);
    }

    public static TaskExecutorException UNKNOWN_EXCEPTION(Throwable cause) {
        return new TaskExecutorException(SC_SERVER_FAILURE, "EX-TASK-001", "Unknown exception is occured.", cause);
    }

    public static TaskExecutorException INVALID_TASK_EXECUTION_PARAMS = CUSTOM(SC_BAD_REQUEST, "EX-TASK-002", "Parameter are invalid for execution.");

    public static TaskExecutorException INVALID_TASK_AVERAGE_PARAMS = CUSTOM(SC_BAD_REQUEST, "EX-TASK-003", "Parameter are invalid for average.");

    public static TaskExecutorException ALWAYS_ERROR_FAILED_AGAIN = CUSTOM(SC_BAD_REQUEST, "EX-TASK-004", "always error failed again.");

    public static TaskExecutorException TASK_NOT_FOUND = CUSTOM(SC_BAD_REQUEST, "EX-TASK-005", "Task matcher couldn't be found.");

}
