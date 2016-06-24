package com.sensor.genkun.sensec;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class TempService extends IntentService {

    private int timeRemain = 30;

    public TempService() {
        super("TempService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        timeRemain = 30;
        handler.postDelayed(runnable, 1000);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (timeRemain == 0) {
                Intent intent = new Intent();
                intent.setClass(TempService.this, AuthenticationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Message message = new Message();
                message.what = 1;
                handlerStop.sendMessage(message);
            }
            handler.postDelayed(this, 1000);
            timeRemain--;
        }
    };

    final Handler handlerStop = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what == 1) {
                timeRemain = 0;
                handler.removeCallbacks(runnable);
            }
            super.handleMessage(msg);
        }
    };
}
