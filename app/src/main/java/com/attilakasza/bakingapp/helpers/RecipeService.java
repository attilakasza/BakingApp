package com.attilakasza.bakingapp.helpers;

import com.attilakasza.bakingapp.models.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {

    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();
}