package com.example.karthikeyan.bakingapp;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.karthikeyan.bakingapp.model.Recipe;
import com.example.karthikeyan.bakingapp.model.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.karthikeyan.bakingapp.MainActivityFragment.RECIPE;

public class RecipeStepActivity extends AppCompatActivity implements LifecycleOwner {

    private static final String TAG = "RecipeStepActivity";

    public static final String STEP = "step";

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    LifecycleRegistry registry = new LifecycleRegistry(this);
    RecipeStepViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel = ViewModelProviders.of(this).get(RecipeStepViewModel.class);
        if (savedInstanceState == null) {
            initRecipe();
            initStep();
            Fragment fragment = new RecipeStepPagerFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.recipe_detail_container,fragment).commit();
        }

    }

    private void initRecipe() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new IllegalStateException();
        }
        if (!extras.containsKey(RECIPE)) {
            throw new IllegalStateException();
        }
        Recipe recipe = (Recipe) extras.get(RECIPE);
        viewModel.setRecipe(recipe);
    }

    private void initStep() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new IllegalStateException();
        }
        if (!extras.containsKey(STEP)) {
            throw new IllegalStateException();
        }
        Step step = (Step) extras.get(STEP);
        viewModel.setStep(step);
    }

    @Override
    public Lifecycle getLifecycle() {
        return registry;
    }
}
