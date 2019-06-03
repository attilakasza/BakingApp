package com.attilakasza.bakingapp.fragments;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.adapters.FragmentPagerAdapter;
import com.attilakasza.bakingapp.models.Recipe;
import com.attilakasza.bakingapp.models.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepsFragment extends Fragment {

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.sliding_tabs) TabLayout tabLayout;
    public static String RECIPE = "RECIPE";
    public static String RECIPE_STEP ="RECIPE_STEP";
    private ArrayList<Step> mSteps;
    private ArrayList<Recipe> mRecipes;

    public StepsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this, rootView);

        Bundle arguments = this.getArguments();
        if (arguments != null) {
            mRecipes = arguments.getParcelableArrayList(RECIPE);
            mSteps = arguments.getParcelableArrayList(RECIPE_STEP);
        }

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getActivity(), getChildFragmentManager(), mRecipes);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(mSteps.get(0).getmId()).select();

        return rootView;
    }
}