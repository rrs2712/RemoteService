package com.mdp.lab04.service03.remoteservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final private String ACT = "Act01 MainActivity";
    private Messenger messenger;
    private Messenger replyMessenger;
    private int
        myX = 27,
        myY = 12;
    private String
        myName = "Raul";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(ACT,"onCreate()");

        this.bindService(new Intent(this, RemoteService.class), serviceConnection, Context.BIND_AUTO_CREATE);
        replyMessenger = new Messenger(new MyHandler());
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.d(ACT, "handleMessage");
        }
    }

    public void onClickCountUp(View v)
    {
        Message message = Message.obtain(null, RemoteService.COUNT_UP, 0, 0);

        try
        {
            messenger.send(message);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public void onClickCountDown(View v)
    {
        Message message = Message.obtain(null, RemoteService.COUNT_DOWN, 0, 0);

        try
        {
            messenger.send(message);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public void sendStuff(View v)
    {
        Message message = Message.obtain(null, RemoteService.SEND_STUFF, 0, 0);

        MyParcelable p = new MyParcelable();
        p.x = this.myX;
        p.y = this.myY;
        p.name = this.myName;

        Bundle b = new Bundle();
        b.putParcelable(RemoteService.BUNDLE_LABEL, (Parcelable) p);
        message.setData(b);
        message.replyTo = replyMessenger;

        try
        {
            messenger.send(message);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }

    }

    private ServiceConnection serviceConnection = new ServiceConnection()
    {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(ACT, "onServiceConnected");
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(ACT, "onServiceDisconnected");
            messenger = null;
        }
    };


    @Override
    protected void onStart() {
        Log.d(ACT,"onStart");
        super.onStart();
    }
    @Override
    protected void onResume() {
        Log.d(ACT,"onResume");
        super.onResume();
    }
    @Override
    protected void onPause() {
        Log.d(ACT,"onPause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d(ACT,"onStop");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.d(ACT,"onDestroy");
        super.onDestroy();

    }
}
