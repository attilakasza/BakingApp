package com.attilakasza.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.models.Step;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    final private OnItemClickListener mListener;
    private List<Step> mStep;
    private Context mContext;

    public StepAdapter(List<Step> steps, Context context, OnItemClickListener listener) {
        mStep = steps;
        mContext = context;
        mListener = listener;
    }

    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_step, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        int id = mStep.get(position).getmId();
        String step = mStep.get(position).getmShortDescription();
        holder.textViewStep.setText(id + ". " + step);
    }

    @Override
    public int getItemCount() {
        if (mStep == null) return 0;
        else return mStep.size();
    }

    public interface OnItemClickListener {
        void onClick(Step clickedStep);
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewStep;

        public StepViewHolder(View itemView) {
            super(itemView);
            textViewStep = itemView.findViewById(R.id.tv_step);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(mStep.get(getLayoutPosition()));
        }
    }
}