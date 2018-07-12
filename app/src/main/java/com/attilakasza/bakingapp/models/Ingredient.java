package com.attilakasza.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient implements Parcelable {

    @Expose
    @SerializedName("quantity")
    private String mQuantity;
    @Expose
    @SerializedName("measure")
    private String mMeasure;
    @Expose
    @SerializedName("ingredient")
    private String mIngredient;

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmMeasure() {
        return mMeasure;
    }

    public void setmMeasure(String mMeasure) {
        this.mMeasure = mMeasure;
    }

    public String getmIngredient() {
        return mIngredient;
    }

    public void setmIngredient(String mIngredient) {
        this.mIngredient = mIngredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mQuantity);
        dest.writeString(this.mMeasure);
        dest.writeString(this.mIngredient);
    }

    public Ingredient() {
    }

    protected Ingredient(Parcel in) {
        this.mQuantity = in.readString();
        this.mMeasure = in.readString();
        this.mIngredient = in.readString();
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}