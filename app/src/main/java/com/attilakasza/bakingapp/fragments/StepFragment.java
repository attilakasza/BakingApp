package com.attilakasza.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.models.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.attilakasza.bakingapp.fragments.StepsFragment.RECIPE_STEP;


public class StepFragment extends Fragment {

    @BindView(R.id.tv_step) TextView mDescription;
    private Step mSteps = new Step();

    public StepFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);
        ButterKnife.bind(this, rootView);

        if(savedInstanceState != null) {
            mSteps = savedInstanceState.getParcelable(RECIPE_STEP);
        }
        else {
            mSteps = getArguments().getParcelable(RECIPE_STEP);
        }

        mDescription.setText(mSteps.getmDescription());

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECIPE_STEP, mSteps);
    }
}