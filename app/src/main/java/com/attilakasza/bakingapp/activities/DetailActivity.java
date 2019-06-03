package com.attilakasza.bakingapp.activities;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.adapters.StepAdapter;
import com.attilakasza.bakingapp.fragments.RecipeFragment;
import com.attilakasza.bakingapp.fragments.StepsFragment;
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

        checkConnection();

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle selectedRecipeBundle = getIntent().getExtras();
            mRecipes = Objects.requireNonNull(selectedRecipeBundle).getParcelableArrayList(RECIPE);

            final RecipeFragment fragment = new RecipeFragment();
            fragment.setArguments(selectedRecipeBundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_steps, fragment)
                    .commit();

            if (findViewById(R.id.ll_activity_detail).getTag() != null && findViewById(R.id.ll_activity_detail).getTag().equals("tablet")) {
                final StepsFragment fragment2 = new StepsFragment();
                Bundle stepBundle = new Bundle();
                mSteps = new ArrayList<>();
                mSteps.add(mRecipes.get(0).getmSteps().get(0));
                stepBundle.putParcelableArrayList(RECIPE_STEP, mSteps);
                stepBundle.putParcelableArrayList(RECIPE, mRecipes);
                fragment2.setArguments(stepBundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_step, fragment2)
                        .commit();
            }
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
        if (checkConnection()) {
            final StepsFragment fragment = new StepsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            Bundle stepBundle = new Bundle();
            mSteps = new ArrayList<>();
            mSteps.add(clickedStep);
            stepBundle.putParcelableArrayList(RECIPE_STEP, mSteps);
            stepBundle.putParcelableArrayList(RECIPE, mRecipes);
            fragment.setArguments(stepBundle);

            if (findViewById(R.id.ll_activity_detail).getTag() != null && findViewById(R.id.ll_activity_detail).getTag().equals("tablet")) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_step, fragment)
                        .commit();
            } else {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_steps, fragment).addToBackStack(BACK_RECIPE_STEP)
                        .commit();
            }
        }
    }

    // Method to manually check connection status
    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
        return  isConnected;
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        if (!isConnected) {
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.fragment_container_steps), getResources().getString(R.string.no_internet), Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setTextColor(getResources().getColor(R.color.colorSnackbar));
            snackbar.show();
        }
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}