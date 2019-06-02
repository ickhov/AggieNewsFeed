package com.ickhov.aggienewsfeed.Views;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ickhov.aggienewsfeed.Models.News;
import com.ickhov.aggienewsfeed.R;
import com.ickhov.aggienewsfeed.Views.CustomViews.MediumButton;
import com.ickhov.aggienewsfeed.Views.CustomViews.RegularTextView;

public class NewsDetailActivity extends AppCompatActivity implements View.OnClickListener,
    WebViewFragment.OnFragmentInteractionListener {

    private News news;
    private ImageButton back;
    private MediumButton webView;
    private RegularTextView details;
    private WebViewFragment webViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // get the News object passed from the previous activity
        news = getIntent().getParcelableExtra("item");

        // initialize the views
        setBack();
        setWebView();
        setDetails();
    }

    // set back button
    private void setBack() {
        back = findViewById(R.id.news_detail_back_btn);
        back.setOnClickListener(this);
    }

    // set web view button
    private void setWebView() {
        webView = findViewById(R.id.news_detail_web_view_btn);
        webView.setOnClickListener(this);
    }

    // set detail text view
    private void setDetails() {
        details = findViewById(R.id.news_detail_details);
        details.setText(getDetailText());
    }

    // combine info from News object to make the detail screen
    private String getDetailText() {
        StringBuffer buffer = new StringBuffer();

        // title section
        buffer.append("Title: ");
        buffer.append(news.getTitle());

        buffer.append("\n\n");

        // author section
        buffer.append("Author: ");
        buffer.append(news.getAuthor());

        buffer.append("\n\n");

        // date published section
        buffer.append("Published: ");
        buffer.append(news.getDatePublished());

        buffer.append("\n\n");

        // content section
        buffer.append("Description: ");
        buffer.append(news.getContent());

        return buffer.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_detail_back_btn:
                // start the previous activity to go back
                Intent intent = new Intent(NewsDetailActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.news_detail_web_view_btn:
                // initialize WebViewFragment if it's new
                if (webViewFragment == null)
                    webViewFragment = WebViewFragment.newInstance(news.getUrl());

                // open the WebViewFragment
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_bottom)
                        .replace(R.id.activity_news_detail, webViewFragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
