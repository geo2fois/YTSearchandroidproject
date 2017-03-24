package com.example.geoff.ytandroidproject.models;

/**
 * Created by geoff on 17/03/2017.
 */

public class YoutubeResult {
    private String title;
    private String description;
    private String thumbnails;
    private String author;
    private String id;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public YoutubeResult(String title, String description, String thumbnails, String id, String date, String author) {
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
        this.id = id;
        this.author = author;
    }
}
