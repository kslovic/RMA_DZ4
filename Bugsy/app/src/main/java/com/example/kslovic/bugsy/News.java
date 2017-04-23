package com.example.kslovic.bugsy;

public class News {
    private String title;
    private String pubDate;
    private String category;
    private String description;
    private String imageUrl;
    private String link;

    public News(String title, String pubDate, String category, String description, String imageUrl, String link) {
        this.title = title;
        this.pubDate = pubDate;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
