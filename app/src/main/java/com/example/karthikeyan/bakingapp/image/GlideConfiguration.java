package com.example.karthikeyan.bakingapp.image;


import android.content.Context;

import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.example.karthikeyan.bakingapp.image.model.VideoThumbnailUrl;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by karthikeyanp on 8/19/17.
 */

@GlideModule
public class GlideConfiguration extends AppGlideModule {


    @Override
    public void registerComponents(Context context, Registry registry) {

        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient().newBuilder().build()));
        registry.replace(VideoThumbnailUrl.class, InputStream.class, new VideoThumbnailFactory());
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
