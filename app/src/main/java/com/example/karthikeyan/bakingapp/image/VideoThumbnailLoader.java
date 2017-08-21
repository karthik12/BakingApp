package com.example.karthikeyan.bakingapp.image;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import com.example.karthikeyan.bakingapp.image.model.VideoThumbnailUrl;

import java.io.InputStream;

/**
 * Created by karthikeyanp on 8/19/17.
 */

class VideoThumbnailLoader implements ModelLoader<VideoThumbnailUrl, InputStream> {

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(VideoThumbnailUrl videoThumbnailUrl, int width, int height, Options options) {
     return  new ModelLoader.LoadData<>(new ObjectKey(videoThumbnailUrl),new VideoThumbnailFetcher(videoThumbnailUrl));
    }

    @Override
    public boolean handles(VideoThumbnailUrl videoThumbnailUrl) {
        return true;
    }

}
