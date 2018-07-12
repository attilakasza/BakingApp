package com.attilakasza.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.attilakasza.bakingapp.adapters.RecipeAdapter;
import com.attilakasza.bakingapp.helpers.RecipeService;
import com.attilakasza.bakingapp.helpers.RetrofitBuilder;
import com.attilakasza.bakingapp.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.OnItemClickListener {

    @BindView(R.id.rv_recipe) RecyclerView mRecyclerView;
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

    }
}
