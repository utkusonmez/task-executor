package com.utku.taskexecutor.web.validator;

/**
 * Validator
 */
public interface Validator<T> {

    boolean validate(T item);

    boolean hasNext();

    Validator<T> next();

}
