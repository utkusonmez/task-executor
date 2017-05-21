package com.utku.taskexecutor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TaskExecutorException
 */
public class TaskExecutorException extends Exception {

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

    public static TaskExecutorException UNKNOWN_EXCEPTION(Throwable cause) {
        return new TaskExecutorException(SC_SERVER_FAILURE, "EX-TASK-001", "Unknown exception is occured.", cause);
    }


}
