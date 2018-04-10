package com.star.quick_work.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerHelper {

    private int period;
    Timer timer = new Timer();
    private TimerTask timerTask = null;

    public TimerHelper(int milliseconds) {
        this.period = milliseconds;
    }

    public void startTimer(TimerTask timerTask) {
        if (this.timerTask == null && timerTask != null) {
            if (timer == null) {
                timer = new Timer();
            }
            timer.schedule(timerTask, 0, this.period);
            this.timerTask = timerTask;
        }
    }

    public void cancle() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }
}