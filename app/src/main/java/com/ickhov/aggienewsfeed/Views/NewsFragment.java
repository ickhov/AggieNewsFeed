package com.ickhov.aggienewsfeed.Views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ickhov.aggienewsfeed.Controllers.NewsArrayAdapter;
import com.ickhov.aggienewsfeed.Models.News;
import com.ickhov.aggienewsfeed.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewsFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;
    private List<News> aggieFeed;
    private ProgressBar progressBar;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        progressBar = view.findViewById(R.id.progressBar);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        aggieFeed = new ArrayList<>();

        Thread thread = new Thread(JSONRunnable);
        thread.start();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

    }

    Runnable JSONRunnable = new Runnable() {
        @Override
        public void run() {
            // start a request queue for JSON response
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            // url for JSON response
            String url = "https://aggiefeed.ucdavis.edu/api/v1/activity/public?s=0?l=10";

            fetchJSONResponse(requestQueue, url);
        }
    };

    /*
    // get the aggie news feed in the background
    public static class GetAggieFeed extends AsyncTask<Void, Void, Void> {

        RequestQueue requestQueue;      // start a request queue for JSON response
        String url;                     // url for JSON response
        List<News> aggieFeed;           // store JSON response as News Model

        public GetAggieFeed (Context context){
            requestQueue = Volley.newRequestQueue(context);
            url = "https://aggiefeed.ucdavis.edu/api/v1/activity/public?s=0?l=10";
            aggieFeed = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fetchJSONResponse(requestQueue, url, aggieFeed);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            for (News n: aggieFeed) {
                list.add(n);
            }

        }
    }
    */

    private void fetchJSONResponse(RequestQueue requestQueue, String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        parseJSONResponse(response);
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("JSON Error", error.toString());
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    private void parseJSONResponse(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject object = response.getJSONObject(i);

                String title = Html.fromHtml(object.getString("title")).toString();
                String author =  Html.fromHtml(object.getJSONObject("actor").getString ("displayName")).toString();
                String datePublished =  object.getString("published").split("T")[0];

                JSONObject details = object.getJSONObject("object");
                String newsURL =  details.getJSONObject("ucdEdusModel").getString ("url");
                String content =  Html.fromHtml(details.getString("content")).toString();

                News news = new News(title, author, datePublished, newsURL, content);
                aggieFeed.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressBar.setVisibility(View.GONE);
        NewsArrayAdapter adapter = new NewsArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, aggieFeed);
        setListAdapter(adapter);
    }
}
