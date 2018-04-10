package com.star.quick_work.observer;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stars on 2018/1/31.
 */
public class TemplateObservable<T> {
    private List<TemplateObserver<T>> observers = new ArrayList<>();
    private Handler uiHandler;

    public TemplateObservable(Context context) {
        uiHandler = new Handler(context.getMainLooper());
    }

    synchronized public void registerObserver(TemplateObserver observer, boolean register) {
        if (observer == null) {
            return;
        }
        if (register) {
            observers.add(observer);
        } else {
            observers.remove(observer);
        }
    }

    synchronized public void notifyChanged(final T message) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                for (TemplateObserver observer : observers) {
                    observer.onDataChanged(message);
                }
            }
        });
    }
}
