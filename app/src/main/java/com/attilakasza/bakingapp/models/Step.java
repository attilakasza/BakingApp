package com.attilakasza.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step implements Parcelable {

    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("shortDescription")
    private String mShortDescription;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("videoURL")
    private String mVideoUrl;
    @Expose
    @SerializedName("thumbnailURL")
    private String mThumbnailUrl;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public void setmShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmVideoUrl() {
        return mVideoUrl;
    }

    public void setmVideoUrl(String mVideoUrl) {
        this.mVideoUrl = mVideoUrl;
    }

    public String getmThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setmThumbnailUrl(String mThumbnailUrl) {
        this.mThumbnailUrl = mThumbnailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mShortDescription);
        dest.writeString(this.mDescription);
        dest.writeString(this.mVideoUrl);
        dest.writeString(this.mThumbnailUrl);
    }

    public Step() {
    }

    protected Step(Parcel in) {
        this.mId = in.readInt();
        this.mShortDescription = in.readString();
        this.mDescription = in.readString();
        this.mVideoUrl = in.readString();
        this.mThumbnailUrl = in.readString();
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}