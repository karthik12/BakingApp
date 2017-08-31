package com.example.karthikeyan.bakingapp.image.model;

/**
 * Created by karthikeyanp on 8/31/17.
 */

public class VideoThumbnailUrl {
    public VideoThumbnailUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    @Override
    public int hashCode() {
        return 31 * url.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof VideoThumbnailUrl)) {
            return false;
        }

        VideoThumbnailUrl thumbnailUrl = (VideoThumbnailUrl) o;

        return thumbnailUrl.url.equals(this.url);

    }
}
