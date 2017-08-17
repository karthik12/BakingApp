package com.example.karthikeyan.bakingapp;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.karthikeyan.bakingapp.model.Recipe;

import java.util.List;

/**
 * Created by karthikeyan on 06/08/17.
 */

public class MainActivityFragment extends LifecycleFragment{

    private static final String TAG = "MainActivityFragment";

    RecipeAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RecipeViewModel recipeViewModel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       recipeViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
        recipeViewModel.getRecipes().observe(this, recipes -> {
            Log.d(TAG, "Got Recipes");
            if (recipes != null) {
                setRecipeVisible(true);
                recipes.forEach(recipe -> {
                    Log.d(TAG, recipe.toString());
                });
               adapter.updateRecipes(recipes);
            }
        });


        int gridColumn = getResources().getInteger(R.integer.grid_column_number);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), gridColumn));
        adapter = new RecipeAdapter(recipeViewModel.getRecipes() ,this::onRecipeClicked);

        recyclerView.setAdapter(adapter);

    }

    void onRecipeClicked(Integer position){

        Log.d(TAG, "onRecipeClicked() called with: recipe = [" + position + "]");

    }

    public void setRecipeVisible(boolean recipeVisible) {
        recyclerView.setVisibility(recipeVisible?View.VISIBLE:View.GONE);
        progressBar.setVisibility(!recipeVisible?View.VISIBLE:View.GONE);
    }
}
