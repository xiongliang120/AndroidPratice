package com.wbtech.test_sample;

import android.os.Parcel;
import android.os.Parcelable;

public class TestParacelable implements Parcelable {
    public String keyword;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.keyword);
    }


    protected TestParacelable(Parcel in) {
        this.keyword = in.readString();
    }

    public static final Creator<TestParacelable> CREATOR = new Creator<TestParacelable>() {
        @Override
        public TestParacelable createFromParcel(Parcel in) {
            return new TestParacelable(in);
        }

        @Override
        public TestParacelable[] newArray(int size) {
            return new TestParacelable[size];
        }
    };
}
