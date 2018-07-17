package com.attilakasza.bakingapp.activities;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.adapters.RecipeAdapter;
import com.attilakasza.bakingapp.helpers.RecipeService;
import com.attilakasza.bakingapp.helpers.RetrofitBuilder;
import com.attilakasza.bakingapp.models.Recipe;
import com.attilakasza.bakingapp.widget.IngredientWidgetProvider;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.OnItemClickListener {

    @BindView(R.id.rv_recipe) RecyclerView mRecyclerView;
    public static String RECIPE = "RECIPE";
    public static String STORED_RECIPE = "STORED_RECIPE";
    private ArrayList<Recipe> mRecipes;
    private RecipeAdapter mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecipeService recipeService = RetrofitBuilder.getRecipeService();
        final Call<ArrayList<Recipe>> recipe = recipeService.getRecipe();

        recipe.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {

                mRecipes = response.body();
                mRecipeAdapter = new RecipeAdapter(mRecipes, MainActivity.this, MainActivity.this);
                mRecyclerView.setAdapter(mRecipeAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
            }
        });

    }

    @Override
    public void onClick(Recipe clickedRecipe) {
        Bundle bundle = new Bundle();
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(clickedRecipe);
        bundle.putParcelableArrayList(RECIPE, recipes);

        int cakeId = clickedRecipe.getmId();
        int yellowCakeId = 3;
        if (cakeId == yellowCakeId) {
            for (int i = 7; i < 13; i++) {
                clickedRecipe.getmSteps().get(i).setmId(i);
            }
        }

        final Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

        Context context = getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(STORED_RECIPE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(clickedRecipe);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(STORED_RECIPE, json);
        editor.apply();

        Intent updateIntent = new Intent(context, IngredientWidgetProvider.class);
        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        context.sendBroadcast(updateIntent);
    }
}
