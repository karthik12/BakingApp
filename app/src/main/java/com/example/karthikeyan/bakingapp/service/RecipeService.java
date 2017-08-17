package com.example.karthikeyan.bakingapp.service;

import com.example.karthikeyan.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by karthikeyan on 06/08/17.
 */

public interface RecipeService {
    @GET("android-baking-app-json")
    Call<List<Recipe>> recipes();
}
