package com.attilakasza.bakingapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.activities.DetailActivity;
import com.attilakasza.bakingapp.adapters.StepAdapter;
import com.attilakasza.bakingapp.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.attilakasza.bakingapp.activities.DetailActivity.RECIPE;


public class RecipeFragment extends Fragment {

    @BindView(R.id.rv_step) RecyclerView mRecyclerView;
    private StepAdapter mStepAdapter;
    private ArrayList<Recipe> mRecipes;


    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, rootView);

        mRecipes = getArguments().getParcelableArrayList(RECIPE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStepAdapter = new StepAdapter(mRecipes.get(0).getmSteps(), getContext(), (DetailActivity)getActivity());
        mRecyclerView.setAdapter(mStepAdapter);

        return rootView;
    }
}