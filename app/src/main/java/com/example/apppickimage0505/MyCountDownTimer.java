package com.example.apppickimage0505;

import android.os.CountDownTimer;

public class MyCountDownTimer {
    public static MyCountDownTimer instance;
    private CountDownTimer countDownTimer;
    private MyCountDownTimer()
    {

    }

    public static MyCountDownTimer getInstance()
    {
        if(instance==null)
        {
            instance=new MyCountDownTimer();
        }
        return instance;
    }

    public void countDown(long totalTime, long interval, OnListenerMyCountDownTimer onListenerMyCountDownTimer)
    {
        if (countDownTimer!=null)
        {
            countDownTimer.cancel();
            countDownTimer=null;
        }

        countDownTimer=new CountDownTimer(totalTime,interval) {
            @Override
            public void onTick(long milliUntilFinished) {
                if(milliUntilFinished>=1000)
                {
                    if(onListenerMyCountDownTimer!=null)
                    {
                        onListenerMyCountDownTimer.onTick(milliUntilFinished);
                    }
                }
            }

            @Override
            public void onFinish() {
                if(onListenerMyCountDownTimer!=null)
                {
                    onListenerMyCountDownTimer.onFinish();
                }
            }
        }.start();
    }

    public void cancelTime()
    {
        if(countDownTimer!=null)
        {
            countDownTimer.cancel();
            countDownTimer=null;
        }
    }

    interface OnListenerMyCountDownTimer
    {
        void onTick(long currentTime);
        void onFinish();
    }
}
