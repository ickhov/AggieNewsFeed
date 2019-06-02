package com.ickhov.aggienewsfeed.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ickhov.aggienewsfeed.Models.News;
import com.ickhov.aggienewsfeed.R;

public class MainActivity extends AppCompatActivity implements
    NewsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onItemClicked(News newsItem) {
        Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
        
    }
}
