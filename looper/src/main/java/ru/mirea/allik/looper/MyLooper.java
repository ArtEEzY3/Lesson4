package ru.mirea.allik.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class MyLooper extends Thread{
    public Handler mHandler;
    private Handler mainHandler;
    public MyLooper(Handler mainThreadHandler) {
        mainHandler =mainThreadHandler;
    }
    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                String data = msg.getData().getString("KEY");
                int delay = msg.getData().getInt("OLD");
                Log.d("MyLooper get message: ", data);
                Log.d("MyLooper get message: ", String.valueOf(delay));
                Message message = new Message();
                Bundle bundle = new Bundle();
                SystemClock.sleep(delay*1000);
                bundle.putString("result", String.format("Возраст студента %s - %d год ",data,delay));
                message.setData(bundle);
// Send the message back to main thread message queue use main thread message Handler.
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}
