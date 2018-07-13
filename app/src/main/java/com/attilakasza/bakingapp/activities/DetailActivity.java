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

public class DetailActivity extends AppCompatActivity implements StepAdapter.OnItemClickListener, ConnectivityReceiver.ConnectivityReceiverListener {

    public static String RECIPE = "RECIPE";
    private ArrayList<Recipe> mRecipes;
    private String mRecipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle selectedRecipeBundle = getIntent().getExtras();
        mRecipes = selectedRecipeBundle.getParcelableArrayList(RECIPE);
        mRecipeName = mRecipes.get(0).getmName();
        getSupportActionBar().setTitle(mRecipeName);

        final RecipeFragment fragment = new RecipeFragment();
        fragment.setArguments(selectedRecipeBundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onClick(Step clickedStep) {

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }
}