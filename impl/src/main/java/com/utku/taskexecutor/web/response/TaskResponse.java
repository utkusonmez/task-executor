package com.utku.taskexecutor.web.response;

import java.io.Serializable;

/**
 * TaskResponse
 */
public class TaskResponse<T> implements Serializable {

    public static <T> Builder<T> builder(){
        return new Builder<T>();
    }

    private String call;
    private final String code;
    private final String description;
    private final T data;

    public TaskResponse(String call, String code, String description, T data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public void call(String call){
        this.call = call;
    }

    public String call() {
        return call;
    }

    public String code() {
        return code;
    }

    public String description() {
        return description;
    }

    public T data() {
        return data;
    }

    public static class Builder<T>{
        private String call = "";
        private String code = "";
        private String description = "";
        private T data = null;

        public Builder<T> call(String call) {
            this.call = call;
            return this;
        }

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> description(String description) {
            this.description = description;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public TaskResponse<T> build(){
            return new TaskResponse<T>(call, code, description, data);
        }
    }


}
