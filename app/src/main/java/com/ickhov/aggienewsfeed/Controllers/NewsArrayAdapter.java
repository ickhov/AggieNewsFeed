package com.ickhov.aggienewsfeed.Controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ickhov.aggienewsfeed.Models.News;
import com.ickhov.aggienewsfeed.R;
import com.ickhov.aggienewsfeed.Views.CustomViews.MediumTextView;
import com.ickhov.aggienewsfeed.Views.CustomViews.RegularTextView;

import java.util.List;

public class NewsArrayAdapter extends ArrayAdapter<News> {

    public NewsArrayAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position,@NonNull View convertView, @NonNull ViewGroup parent) {

        // get news item
        News news = getItem(position);

        // check whether convertView is new or reused
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        // initialize title and author view
        RegularTextView title = convertView.findViewById(R.id.news_title);
        MediumTextView author = convertView.findViewById(R.id.news_author);

        // set the texts for title and author view
        title.setText(news.getTitle());

        String author_ = "By: " + news.getAuthor();
        author.setText(author_);

        return convertView;
    }
}
