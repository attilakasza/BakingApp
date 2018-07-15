package com.attilakasza.bakingapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.attilakasza.bakingapp.fragments.StepFragment;
import com.attilakasza.bakingapp.models.Recipe;

import java.util.ArrayList;

import static com.attilakasza.bakingapp.fragments.StepsFragment.RECIPE_STEP;

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private Context mContext;
    private ArrayList<Recipe> mRecipes;


    public FragmentPagerAdapter(Context context, FragmentManager fm, ArrayList<Recipe> recipes) {
        super(fm);
        mContext = context;
        mRecipes = recipes;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        final StepFragment fragment = new StepFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_STEP, mRecipes.get(0).getmSteps().get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return mRecipes.get(0).getmSteps().size();
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return position + ". step";
    }
}