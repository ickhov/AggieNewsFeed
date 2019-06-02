package com.ickhov.aggienewsfeed.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ickhov.aggienewsfeed.Models.News;
import com.ickhov.aggienewsfeed.R;

public class NewsDetailActivity extends AppCompatActivity {

    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        news = getIntent().getParcelableExtra("item");


    }
}
