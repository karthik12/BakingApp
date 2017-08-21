package com.example.karthikeyan.bakingapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karthikeyan.bakingapp.image.GlideApp;
import com.example.karthikeyan.bakingapp.model.Ingredient;
import com.example.karthikeyan.bakingapp.model.Recipe;
import com.example.karthikeyan.bakingapp.model.Step;
import com.example.karthikeyan.bakingapp.image.model.VideoThumbnailUrl;

import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by karthikeyanp on 8/18/17.
 */

class RecipeStepsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecipeStepsAdapter";
    public static final int INGREDIENT = 1;
    public static final int STEP = 2;
    private Consumer<Integer> clickListener;

    private Recipe recipe;

    RecipeStepsAdapter(Recipe recipe, Consumer<Integer> onRecipeClicked) {
        this.recipe = recipe;
        clickListener = onRecipeClicked;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder;
        View view;
        switch (viewType) {
            case INGREDIENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_ingredient, parent, false);
                holder = new IngredientViewHolder(view);
                break;

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_step, parent, false);
                holder = new StepViewHolder(view);

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (holder == null) {
            return;
        }

        switch (holder.getItemViewType()) {
            case INGREDIENT:
                Ingredient ingredient = recipe.ingredients.get(position);
                ((IngredientViewHolder) holder).recipeIngredientName.setText(ingredient.ingredient);
                String quantity = App.getContext().getString(R.string.ingredient_quantity_text, ingredient.quantity, ingredient.measure);
                ((IngredientViewHolder) holder).getRecipeIngredientQuantity.setText(quantity);
                break;
            default:
                position = position - recipe.ingredients.size();
                Step step = recipe.steps.get(position);
                ((StepViewHolder) holder).recipeStepText.setText(step.shortDescription);
                ImageView recipeStepImage = ((StepViewHolder) holder).recipeStepImage;
                if (step.thumbnailURL.isEmpty()) {
                    Log.i(TAG, "onBindViewHolder: thumbnail is empty");
                    if(step.videoURL.isEmpty()){
                        Log.i(TAG, "onBindViewHolder: video url is empty");
                    }
                    GlideApp.with(holder.itemView).load(new VideoThumbnailUrl(step.videoURL))
                            .placeholder(R.drawable.recipe_icon_md).into(recipeStepImage);

                }else {
                    GlideApp.with(holder.itemView)
                            .load(step.thumbnailURL)
                            .placeholder(R.drawable.recipe_icon_md)
                            .centerInside()
                            .into(recipeStepImage);
                }
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position < recipe.ingredients.size()) {
            return INGREDIENT;
        }
        return STEP;
    }

    @Override
    public int getItemCount() {
        if (recipe == null)
            return 0;
        return recipe.ingredients.size() + recipe.steps.size();
    }

    class StepViewHolder extends ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipe_step_image)
        ImageView recipeStepImage;
        @BindView(R.id.recipe_step_title)
        TextView recipeStepText;

        StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.accept(getLayoutPosition() - recipe.ingredients.size());
        }
    }

    class IngredientViewHolder extends ViewHolder {

        @BindView(R.id.recipe_ingredient_name)
        TextView recipeIngredientName;
        @BindView(R.id.recipe_ingredient_quantity)
        TextView getRecipeIngredientQuantity;

        IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
