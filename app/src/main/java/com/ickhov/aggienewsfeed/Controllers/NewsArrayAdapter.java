package com.ickhov.aggienewsfeed.Controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ickhov.aggienewsfeed.Models.News;
import com.ickhov.aggienewsfeed.R;
import com.ickhov.aggienewsfeed.Views.CustomViews.RegularTextView;

import java.util.List;

public class NewsArrayAdapter extends ArrayAdapter<News> {

    public NewsArrayAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position,@NonNull View convertView, @NonNull ViewGroup parent) {

        News news = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        RegularTextView title = convertView.findViewById(R.id.news_title);
        RegularTextView author = convertView.findViewById(R.id.news_author);

        title.setText(news.getTitle());
        author.setText(news.getAuthor());

        return convertView;
    }
}
