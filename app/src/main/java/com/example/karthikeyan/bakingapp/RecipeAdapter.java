package com.example.karthikeyan.bakingapp;

import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karthikeyan.bakingapp.model.Recipe;
import com.example.karthikeyan.bakingapp.model.Step;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by karthikeyan on 06/08/17.
 */

class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    Consumer clickListener;
    List<Recipe> recipes;

    public RecipeAdapter(LiveData<List<Recipe>> recipes, Consumer<Integer> onRecipeClicked) {
        this.recipes = recipes.getValue();
        clickListener = onRecipeClicked;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder != null) {
            Recipe recipe = recipes.get(position);
            holder.recipeTitle.setText(recipe.name);
            if (!recipes.get(position).image.isEmpty()) {
                Picasso.with(holder.itemView.getContext())
                        .load(recipes.get(position).image)
                        .error(R.drawable.ic_error_outline_black_24dp).centerCrop()
                        .placeholder(R.drawable.ic_donut_large_black_24dp).centerCrop()
                        .into(holder.recipeImage);
            } /*else {
                String lastStepWithVideo = getLastStepWithVideo(recipe.steps);
                Flowable<Bitmap> bitmapObservable = Flowable.fromCallable(() -> {
                    Bitmap bitmap = null;
                    MediaMetadataRetriever mediaMetadataRetriever = null;
                    try {
                        mediaMetadataRetriever = new MediaMetadataRetriever();
                        mediaMetadataRetriever.setDataSource(lastStepWithVideo, new HashMap<String, String>());
                        //   mediaMetadataRetriever.setDataSource(videoPath);
                        bitmap = mediaMetadataRetriever.getFrameAtTime();
                    } catch (Exception e) {
                        e.printStackTrace();

                    } finally {
                        if (mediaMetadataRetriever != null)
                            mediaMetadataRetriever.release();
                    }
                    return bitmap;
                });
                bitmapObservable.forEach(bitmap -> {
                     holder.recipeImage.setImageBitmap(bitmap);
                });
            }*/

        }
    }


    private String getLastStepWithVideo(List<Step> steps) {

        // String.valueOf(steps.stream().filter(s-> s.videoURL !=null && !s.videoURL.isEmpty()).findFirst());
        for (Step step : steps) {
            if (step.videoURL != null && !step.videoURL.isEmpty()) {
                return step.videoURL;
            }
        }
        return "";
    }

    @Override
    public int getItemCount() {
        if (recipes != null) {
            return recipes.size();
        }
        return 0;
    }

    void updateRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipe_image)
        ImageView recipeImage;

        @BindView(R.id.recipe_title)
        TextView recipeTitle;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.accept(getLayoutPosition());
        }
    }

}
