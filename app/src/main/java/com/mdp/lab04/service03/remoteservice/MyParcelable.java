package com.mdp.lab04.service03.remoteservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by rrs27 on 2016-11-13.
 */

public class MyParcelable implements Parcelable {
    int x, y;
    String name;
    private final String ACT = "Act04 MyParcelable";

    public MyParcelable(){
        Log.d(ACT, "MyParcelable()");
    }

    public MyParcelable(Parcel in)
    {
        Log.d(ACT, "MyParcelable(Parcel in)");
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.x);
        out.writeInt(this.y);
        out.writeString(name);
    }

    private void readFromParcel(Parcel in){
        this.x = in.readInt();
        this.y = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<MyParcelable> CREATOR = new Creator<MyParcelable>() {
        @Override
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        @Override
        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };

}
