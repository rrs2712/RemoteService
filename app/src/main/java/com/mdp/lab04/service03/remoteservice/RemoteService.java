package com.mdp.lab04.service03.remoteservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import java.text.SimpleDateFormat;
import java.util.Calendar;


public class RemoteService extends Service {
//    public RemoteService() {}

    final private String ACT ="Act02 RemoteService";
    private Counter counter = new Counter();

    // Messenger is a reference to a Handler,
    // which others can use to send messages to it
    private Messenger messenger;

    public static final int
        COUNT_UP = 0,
        COUNT_DOWN = 1,
        SEND_STUFF = 2;

    public static final String
        BUNDLE_LABEL = "myParcel";

    @Override
    public void onCreate() {
        Log.d(ACT, "onCreate");
        messenger = new Messenger(new MyHandler());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(ACT, "onStartCommand");
        throw new RuntimeException("You should not start this service");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(ACT, "onBind");
        return messenger.getBinder();
    }

    @Override
    public void onDestroy() {
        Log.d(ACT, "onDestroy");
        //Stops Counter
        counter.running = false;
        counter = null;
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(ACT, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(ACT, "onUnbind");
        return super.onUnbind(intent);
    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case COUNT_UP:
                    countUp();
                    break;
                case COUNT_DOWN:
                    countDown();
                    break;
                case SEND_STUFF:
                    MyParcelable p = msg.getData().getParcelable(BUNDLE_LABEL);
                    Log.d(ACT, p.x + " " + p.y + " " + p.name);
                    Messenger replyto = msg.replyTo;
                    Message reply = Message.obtain();
                    try {
                        replyto.send(reply);
                    } catch (RemoteException e) {
                        Log.d(ACT, e.toString());
                    }
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    public void countUp() {
        Log.d(ACT, "countUp");
        counter.direction = true;
    }

    public void countDown() {
        Log.d(ACT, "countDown");
        counter.direction = false;
    }

}
