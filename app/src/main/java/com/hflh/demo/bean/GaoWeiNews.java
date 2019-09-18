package com.hflh.demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zx on 2017/10/23.
 */

public class GaoWeiNews  implements Parcelable {

    /**
     *   public String id;
     public String title;
     public String publishTime;
     public String publishTimeLong;
     public String sourceMedia;
     public String author;
     public String simHashCount;
     public String content;
     public String digest;
     public String url;
     public String reqHash;
     public String simHash;
     public String degreeStr;
     public String evaluateStr;
     public String areaName;
     public String mediaTypeId;*/

    public String id;
    public String title;
    public String areaName;
    public String publishTime;
    public String publishMedia;
    public String sourceMedia;
    public String degreeStr;
    public String evaluateStr;
    public String simHash;
    public String mediaTypeId;


    protected GaoWeiNews(Parcel in) {
        id = in.readString();
        title = in.readString();
        areaName = in.readString();
        publishTime = in.readString();
        publishMedia = in.readString();
        sourceMedia = in.readString();
        degreeStr = in.readString();
        evaluateStr = in.readString();
        simHash = in.readString();
        mediaTypeId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(areaName);
        dest.writeString(publishTime);
        dest.writeString(publishMedia);
        dest.writeString(sourceMedia);
        dest.writeString(degreeStr);
        dest.writeString(evaluateStr);
        dest.writeString(simHash);
        dest.writeString(mediaTypeId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GaoWeiNews> CREATOR = new Creator<GaoWeiNews>() {
        @Override
        public GaoWeiNews createFromParcel(Parcel in) {
            return new GaoWeiNews(in);
        }

        @Override
        public GaoWeiNews[] newArray(int size) {
            return new GaoWeiNews[size];
        }
    };

    @Override
    public String toString() {
        return "GaoWeiNews{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", areaName='" + areaName + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", publishMedia='" + publishMedia + '\'' +
                ", sourceMedia='" + sourceMedia + '\'' +
                ", degreeStr='" + degreeStr + '\'' +
                ", evaluateStr='" + evaluateStr + '\'' +
                ", simHash='" + simHash + '\'' +
                ", mediaTypeId='" + mediaTypeId + '\'' +
                '}';
    }
}
