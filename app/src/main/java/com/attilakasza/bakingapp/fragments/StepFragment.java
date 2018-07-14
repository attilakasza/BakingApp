package com.attilakasza.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.models.Recipe;
import com.attilakasza.bakingapp.models.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.attilakasza.bakingapp.activities.DetailActivity.RECIPE;
import static com.attilakasza.bakingapp.activities.DetailActivity.RECIPE_STEP;


public class StepFragment extends Fragment {

    @BindView(R.id.tv_step) TextView mDescription;
    private ArrayList<Recipe> mRecipes;
    private ArrayList<Step> mSteps = new ArrayList<>();

    public StepFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);
        ButterKnife.bind(this, rootView);

        if(savedInstanceState != null) {
            mSteps = savedInstanceState.getParcelableArrayList(RECIPE_STEP);
        }
        else {
            mSteps = getArguments().getParcelableArrayList(RECIPE_STEP);
            mRecipes = getArguments().getParcelableArrayList(RECIPE);
        }

        mDescription.setText(mSteps.get(0).getmDescription());

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE, mRecipes);
        outState.putParcelableArrayList(RECIPE_STEP, mSteps);
    }
}