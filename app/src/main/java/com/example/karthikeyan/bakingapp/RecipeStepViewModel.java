package com.example.karthikeyan.bakingapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.karthikeyan.bakingapp.model.Recipe;
import com.example.karthikeyan.bakingapp.model.Step;

/**
 * Created by karthikeyanp on 8/19/17.
 */

public class RecipeStepViewModel extends ViewModel {

    private MutableLiveData<Recipe> recipe = new MutableLiveData<>();

    private MutableLiveData<Step> step = new MutableLiveData<>();


    LiveData<Recipe> getRecipe() {
        return recipe;
    }

    LiveData<Step> getStep() {
        return step;
    }

    void setRecipe(Recipe recipe) {
        this.recipe.setValue(recipe);
    }

    void setStep(Step step) {
        this.step.setValue(step);
    }
}
