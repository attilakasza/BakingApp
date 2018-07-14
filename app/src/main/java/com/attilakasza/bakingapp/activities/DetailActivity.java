package com.attilakasza.bakingapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.adapters.StepAdapter;
import com.attilakasza.bakingapp.fragments.RecipeFragment;
import com.attilakasza.bakingapp.helpers.ConnectivityReceiver;
import com.attilakasza.bakingapp.models.Recipe;
import com.attilakasza.bakingapp.models.Step;

import java.util.ArrayList;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements StepAdapter.OnItemClickListener, ConnectivityReceiver.ConnectivityReceiverListener {

    public static String RECIPE = "RECIPE";
    private ArrayList<Recipe> mRecipes;

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
        }

        String recipeName = Objects.requireNonNull(mRecipes).get(0).getmName();
        getSupportActionBar().setTitle(recipeName);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPE, mRecipes);
    }

    @Override
    public void onClick(Step clickedStep) {

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }
}