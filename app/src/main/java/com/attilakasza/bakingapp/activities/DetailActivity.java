package com.attilakasza.bakingapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.adapters.StepAdapter;
import com.attilakasza.bakingapp.fragments.RecipeFragment;
import com.attilakasza.bakingapp.fragments.StepFragment;
import com.attilakasza.bakingapp.helpers.ConnectivityReceiver;
import com.attilakasza.bakingapp.models.Recipe;
import com.attilakasza.bakingapp.models.Step;

import java.util.ArrayList;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements StepAdapter.OnItemClickListener, ConnectivityReceiver.ConnectivityReceiverListener {

    public static String RECIPE = "RECIPE";
    public static String RECIPE_STEP ="RECIPE_STEP";
    public static String BACK_RECIPE_STEP = "BACK_RECIPE_STEP";
    private ArrayList<Recipe> mRecipes;
    private ArrayList<Step> mSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle selectedRecipeBundle = getIntent().getExtras();
            mRecipes = Objects.requireNonNull(selectedRecipeBundle).getParcelableArrayList(RECIPE);

            final RecipeFragment fragment = new RecipeFragment();
            fragment.setArguments(selectedRecipeBundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        } else {
            mRecipes = savedInstanceState.getParcelableArrayList(RECIPE);
            mSteps = savedInstanceState.getParcelableArrayList(RECIPE_STEP);
        }

        String recipeName = Objects.requireNonNull(mRecipes).get(0).getmName();
        getSupportActionBar().setTitle(recipeName);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE, mRecipes);
        outState.putParcelableArrayList(RECIPE_STEP, mSteps);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(Step clickedStep) {
        final StepFragment fragment = new StepFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        Bundle stepBundle = new Bundle();
        mSteps = new ArrayList<>();
        mSteps.add(clickedStep);
        stepBundle.putParcelableArrayList(RECIPE_STEP, mSteps);
        stepBundle.putParcelableArrayList(RECIPE, mRecipes);
        fragment.setArguments(stepBundle);

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment).addToBackStack(BACK_RECIPE_STEP)
                .commit();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }
}