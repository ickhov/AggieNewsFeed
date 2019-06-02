package com.ickhov.aggienewsfeed.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.ickhov.aggienewsfeed.Models.News;
import com.ickhov.aggienewsfeed.R;
import com.ickhov.aggienewsfeed.Views.CustomViews.MediumButton;
import com.ickhov.aggienewsfeed.Views.CustomViews.RegularTextView;

public class NewsDetailActivity extends AppCompatActivity {

    private News news;
    private ImageButton back;
    private MediumButton webView;
    private RegularTextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        news = getIntent().getParcelableExtra("item");
        setBack();
        setWebView();
        setDetails();
    }

    // set back button
    private void setBack() {
        back = findViewById(R.id.news_detail_back_btn);
    }

    // set web view button
    private void setWebView() {
        webView = findViewById(R.id.news_detail_web_view_btn);
    }

    // set detail text view
    private void setDetails() {
        details = findViewById(R.id.news_detail_details);
    }
}
