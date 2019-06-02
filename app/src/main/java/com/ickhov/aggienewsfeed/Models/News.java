package com.ickhov.aggienewsfeed.Models;

// News Model to store data for a news item
public class News {

    private String title;
    private String author;
    private String datePublished;
    private String url;
    private String content;

    public News(String title, String author, String datePublished, String url, String content) {
        this.title = title;
        this.author = author;
        this.datePublished = datePublished;
        this.url = url;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
