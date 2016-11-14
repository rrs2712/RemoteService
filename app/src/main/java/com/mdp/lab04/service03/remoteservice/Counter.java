package com.mdp.lab04.service03.remoteservice;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rrs27 on 2016-11-13.
 */

public class Counter extends Thread implements Runnable {

    public boolean direction = true;
    public int count = 0;
    public boolean running = true;
    private int X_SECONDS = 5000;
    private final String ACT = "Act03 Counter";
    final private String DATE_TEMPLATE = "yyyy/MM/dd HH:mm:ss";

    public Counter(){
//      Causes this thread to begin execution;
//      the Java Virtual Machine calls the run method of this thread.
        this.start();
    }

    @Override
    public void run() {
        Log.d(ACT,"Counter thread is now running");

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TEMPLATE);
        Date IniDate = Calendar.getInstance().getTime();

        while (this.running){
            try {
                Thread.sleep(X_SECONDS);
            } catch (InterruptedException e) {
                Log.d(ACT, e.toString());
                return;
            }

            if (direction){
                count++;
            }else {
                count--;
            }

            Log.d(ACT,"Counting: " + count);
        }

        String aDateAndTime = sdf.format(IniDate);
        Log.d(ACT, String.format("Counter thread ini " + aDateAndTime));

        aDateAndTime = sdf.format(Calendar.getInstance().getTime());
        Log.d(ACT, String.format("Counter thread end " + aDateAndTime));
    }
}
