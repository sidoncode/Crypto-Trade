package com.cryptocurrencybestrate.ethereum.ActivityPackage;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cryptocurrencybestrate.ethereum.AdapterPackage.Models.News;
import com.cryptocurrencybestrate.ethereum.AdapterPackage.NewsAdapter;
import com.cryptocurrencybestrate.ethereum.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> newsList;

    AdView mAdView;

    String BASE_URL = "https://min-api.cryptocompare.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recycler_view);
        newsList = new ArrayList<>();
        prepareNews();
        adapter = new NewsAdapter(this, newsList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void prepareNews() {

        String url = BASE_URL + "/data/v2/news/?lang=EN";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray js = response.getJSONArray("Data");
                    for (int i = 0; i < js.length(); i++) {
                        JSONObject display = js.getJSONObject(i);
                        News n = new News(display.getString("id"),
                                display.getString("title"),
                                display.getJSONObject("source_info").getString("name"),
                                display.getString("imageurl"),
                                display.getString("categories"),
                                display.getJSONObject("source_info").getString("img"),
                                display.getString("body"),
                                display.getLong("published_on"),
                                display.getString("url"));
                        newsList.add(n);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("******", "Error");
            }
        });

        queue.add(jsObjRequest);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
