package com.star.quick_work.slow_onclikc;

import android.view.View;

public abstract class SlowOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        if (ClickUtils.isFastClick()) {
            this.onPreventQuickClick(v);
        }
    }

    abstract public void onPreventQuickClick(View v);
} 