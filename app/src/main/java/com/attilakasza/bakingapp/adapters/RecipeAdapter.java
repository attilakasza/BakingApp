package com.attilakasza.bakingapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    final private OnItemClickListener mListener;
    private ArrayList<Recipe> mRecipes;
    private Context mContext;

    public RecipeAdapter(ArrayList<Recipe> recipes, Context context, OnItemClickListener listener) {
        mRecipes = recipes;
        mContext = context;
        mListener = listener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        String name = mRecipes.get(position).getmName();
        holder.textViewRecipe.setText(name);

        String imageUrl = mRecipes.get(position).getmImage();
        if (!imageUrl.isEmpty()) {
            Picasso.with(mContext)
                    .load(Uri.parse(imageUrl))
                    .into(holder.imageViewRecipe);
        }
    }

    @Override
    public int getItemCount() {
        if (mRecipes == null) return 0;
        else return mRecipes.size();
    }

    public interface OnItemClickListener {
        void onClick(Recipe clickedRecipe);
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewRecipe;
        ImageView imageViewRecipe;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            textViewRecipe = itemView.findViewById(R.id.tv_recipe);
            imageViewRecipe = itemView.findViewById(R.id.iv_recipe);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(mRecipes.get(getLayoutPosition()));
        }
    }
}