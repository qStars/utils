package com.star.quick_work.simple_callback;

/**
 * Created by Stars on 2018/1/26.
 */
public interface SimpleCallback<T> {
    /**
     * @param data
     * @param error
     */
    void onResult(T data, Throwable error);
}
