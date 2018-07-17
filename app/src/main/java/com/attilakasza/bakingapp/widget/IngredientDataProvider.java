package com.attilakasza.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.models.Ingredient;
import com.attilakasza.bakingapp.models.Recipe;
import com.google.gson.Gson;

import static com.attilakasza.bakingapp.activities.MainActivity.STORED_RECIPE;

public class IngredientDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private Recipe mRecipe;
    private Context mContext;

    public IngredientDataProvider(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() { }

    @Override
    public void onDataSetChanged() {
        SharedPreferences preferences = mContext.getSharedPreferences(STORED_RECIPE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String storedRecipe = preferences.getString(STORED_RECIPE, "");
        mRecipe = gson.fromJson(storedRecipe, Recipe.class);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mRecipe.getmIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(mContext.getPackageName(),
                R.layout.ingredient_widget_item);

        if (mRecipe.getmIngredients() != null) {
            Ingredient ingredient = mRecipe.getmIngredients().get(position);
            String quantity = ingredient.getmQuantity() + " " + ingredient.getmMeasure();
            String ingredients = ingredient.getmIngredient();
            views.setTextViewText(R.id.item_quantity, quantity);
            views.setTextViewText(R.id.item_ingredient, ingredients);

            Intent intent = new Intent();
            views.setOnClickFillInIntent(R.id.item_widget, intent);
        }
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}