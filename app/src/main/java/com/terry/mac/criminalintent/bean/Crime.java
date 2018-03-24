package com.terry.mac.criminalintent.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by mac on 2018/3/24.
 */

public class Crime {
    private UUID mid;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public SimpleDateFormat getFormat() {
        return mFormat;
    }

    public void setFormat(SimpleDateFormat format) {
        mFormat = format;
    }

    private SimpleDateFormat mFormat;

    public Crime() {
        mid = UUID.randomUUID();
        mDate = new Date();
        mFormat = new SimpleDateFormat("E yyyy年MM月dd日 HH时mm分ss秒 ");
    }

    public UUID getMid() {
        return mid;
    }

    public void setMid(UUID mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mFormat.format(mDate);
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    @Override
    public String toString() {
        return "Crime{" +
                "mid=" + mid +
                ", mTitle='" + mTitle + '\'' +
                ", mDate=" + mDate +
                ", mSolved=" + mSolved +
                '}';
    }
}
