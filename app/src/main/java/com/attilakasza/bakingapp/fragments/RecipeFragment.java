package com.attilakasza.bakingapp.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.activities.DetailActivity;
import com.attilakasza.bakingapp.adapters.StepAdapter;
import com.attilakasza.bakingapp.models.Ingredient;
import com.attilakasza.bakingapp.models.Recipe;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.attilakasza.bakingapp.activities.DetailActivity.RECIPE;


public class RecipeFragment extends Fragment {

    @BindView(R.id.rv_step) RecyclerView mRecyclerView;
    @BindView(R.id.tv_ingredient) TextView mTextView;
    public static String LIST_STATE = "LIST_STATE";
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<Recipe> mRecipes;
    private Parcelable mListState;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, rootView);

        if(savedInstanceState != null) {
            mRecipes = savedInstanceState.getParcelableArrayList(RECIPE);
            mListState = savedInstanceState.getParcelable(LIST_STATE);
            mLinearLayoutManager = new LinearLayoutManager(getContext());

            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(mListState);
        } else {
            mRecipes = Objects.requireNonNull(getArguments()).getParcelableArrayList(RECIPE);
            mLinearLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
        }

        StepAdapter stepAdapter = new StepAdapter(mRecipes.get(0).getmSteps(), getContext(), (DetailActivity)getActivity());
        mRecyclerView.setAdapter(stepAdapter);

        ArrayList<Ingredient> ingredients = mRecipes.get(0).getmIngredients();

        StringBuilder all = new StringBuilder();
        for (int i=0; i<ingredients.size(); i++) {
            String quantity = ingredients.get(i).getmQuantity();
            String measure = ingredients.get(i).getmMeasure();
            String ingredient = ingredients.get(i).getmIngredient();

            if (i < (ingredients.size()-1)) {
                all.append(quantity).append(" ").append(measure).append("   ").append(ingredient).append("\n");
            } else {
                all.append(quantity).append(" ").append(measure).append("   ").append(ingredient);
            }
        }
        mTextView.setText(all.toString());

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE, mRecipes);
        mListState = mLinearLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE, mListState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mListState != null) {
            mLinearLayoutManager.onRestoreInstanceState(mListState);
        }
    }
}