package com.example.karthikeyan.bakingapp.image;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.example.karthikeyan.bakingapp.image.model.VideoThumbnailUrl;

import java.io.InputStream;

/**
 * Created by karthikeyanp on 8/19/17.
 */

public class VideoThumbnailFactory implements ModelLoaderFactory<VideoThumbnailUrl,InputStream>{
    @Override
    public ModelLoader<VideoThumbnailUrl, InputStream> build(MultiModelLoaderFactory multiFactory) {
        return new VideoThumbnailLoader();
    }

    @Override
    public void teardown() {

    }
}
