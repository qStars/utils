package com.star.quick_work.observer;

/**
 * Created by Stars on 2018/1/31.
 */
public interface TemplateObserver<T> {
    void onDataChanged(T data);
}
