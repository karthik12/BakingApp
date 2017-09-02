package com.example.karthikeyan.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.karthikeyan.bakingapp.App;
import com.example.karthikeyan.bakingapp.R;
import com.example.karthikeyan.bakingapp.RecipeInfoWidgetManager;
import com.example.karthikeyan.bakingapp.model.Ingredient;
import com.example.karthikeyan.bakingapp.model.Recipe;

/**
 * Created by karthikeyanp on 8/22/17.
 */

public class RecipeInfoWidgetRemoteViewService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyRemoteViewFactory();
    }
}

class MyRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private RecipeInfoWidgetManager recipeInfoWidgetManager = new RecipeInfoWidgetManager();

    private Recipe recipe;

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        recipe = recipeInfoWidgetManager.getRecipe();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if(recipe == null){
            return 0;
        }
        if(recipe.ingredients == null || recipe.ingredients.isEmpty()){
            return 0;
        }
        return recipe.ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews views = new RemoteViews(App.getContext().getPackageName(), R.layout.cell_wigdet_recipe_ingredient);

        Ingredient ingredient = recipe.ingredients.get(i);

        views.setTextViewText(R.id.recipe_ingredient_name, ingredient.ingredient);
        views.setTextViewText(R.id.recipe_ingredient_quantity, App.getContext().getString(R.string.ingredient_quantity_text, ingredient.quantity, ingredient.measure));
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
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
