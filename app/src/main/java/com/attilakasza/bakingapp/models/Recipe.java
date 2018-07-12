package com.attilakasza.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Recipe implements Parcelable {

    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("ingredients")
    private ArrayList<Ingredient> mIngredients;
    @Expose
    @SerializedName("steps")
    private ArrayList<Step> mSteps;
    @Expose
    @SerializedName("servings")
    private String mServings;
    @Expose
    @SerializedName("image")
    private String mImage;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public ArrayList<Ingredient> getmIngredients() {
        return mIngredients;
    }

    public void setmIngredients(ArrayList<Ingredient> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public ArrayList<Step> getmSteps() {
        return mSteps;
    }

    public void setmSteps(ArrayList<Step> mSteps) {
        this.mSteps = mSteps;
    }

    public String getmServings() {
        return mServings;
    }

    public void setmServings(String mServings) {
        this.mServings = mServings;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
        dest.writeList(this.mIngredients);
        dest.writeList(this.mSteps);
        dest.writeString(this.mServings);
        dest.writeString(this.mImage);
    }

    public Recipe() {
    }

    protected Recipe(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
        this.mIngredients = new ArrayList<Ingredient>();
        in.readList(this.mIngredients, Ingredient.class.getClassLoader());
        this.mSteps = new ArrayList<Step>();
        in.readList(this.mSteps, Step.class.getClassLoader());
        this.mServings = in.readString();
        this.mImage = in.readString();
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}