package com.ickhov.aggienewsfeed.Models;

import android.os.Parcel;
import android.os.Parcelable;

// News Model to store data for a news item
public class News implements Parcelable {

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

    protected News(Parcel in) {
        title = in.readString();
        author = in.readString();
        datePublished = in.readString();
        url = in.readString();
        content = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(datePublished);
        dest.writeString(url);
        dest.writeString(content);
    }
}
