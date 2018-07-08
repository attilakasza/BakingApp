package com.attilakasza.bakingapp.models;

import java.util.ArrayList;

public class Recipe {

    private int mId;
    private String mName;
    private ArrayList<Ingredient> mIngredients;
    private ArrayList<Step> mSteps;
    private String mServings;
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
}