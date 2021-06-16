package com.cryptotrade.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cryptotrade.ActivityPackage.HomeActivity;
import com.cryptotrade.AdapterPackage.CoinHorizAdapter;
import com.cryptotrade.AdapterPackage.MarketCoinAdapter;
import com.cryptotrade.AdapterPackage.Models.CoinsTickersModel;
import com.cryptotrade.AdapterPackage.Models.News;
import com.cryptotrade.AdapterPackage.NewsAdapter;
import com.cryptotrade.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MarketFragment extends Fragment {
    /**
     * field instance of all views
     */
    View rootView;
    RecyclerView recyclerViewHorizCoin, recyclerViewVerticalCoin;
    LinearLayout btnAddCurrency;

    public ArrayList<String> dataModalArrayList;
    public ArrayList<CoinsTickersModel> apiModels1;

    private MarketCoinAdapter mMarketCoinAdapter;
    private CoinHorizAdapter mCoinHorizAdapter;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> newsList;
    Context mContext;

    SwipeRefreshLayout swiperl;



    public static final String Market_url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=INR&order=current_price&per_page=100&page=1&sparkline=false";
    public TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /**
         * assigning the layout of market and type casting of all child views
         */

        rootView = inflater.inflate(R.layout.layout_market, container, false);

        api_call();
        refresh();

        swiperl = rootView.findViewById(R.id.swiperl);

        //swiperl.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) getActivity());

        swiperl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiperl.setRefreshing(true);
                mMarketCoinAdapter.clearList();
                refresh();
                mMarketCoinAdapter.notifyDataIsChanged();
                swiperl.setRefreshing(false);
                mMarketCoinAdapter.notifyDataIsChanged();
            }
        });

        swiperl.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        /**
         * initializing  views
         */

        initViews();

        /**
         * setting layout manager to the recycler views
         */

        //mTextView = rootView.findViewById(R.id.add_new);
        recyclerViewHorizCoin = rootView.findViewById(R.id.recycler_coin_horizontal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewHorizCoin.setLayoutManager(linearLayoutManager);




        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewVerticalCoin.setLayoutManager(linearLayoutManager1);


        final long period = 5000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try{
                    //refresh_data();// do your task here
                    //call_api();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 0, period);





        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("0.00005" + i);
        }

        //recyclerViewHorizCoin.setAdapter(new CoinHorizAdapter(arrayList, getActivity()));
        //recyclerViewVerticalCoin.setAdapter(new MarketCoinAdapter(arrayList, getActivity()));
        mCoinHorizAdapter = new CoinHorizAdapter(recyclerViewHorizCoin);
        mMarketCoinAdapter =new MarketCoinAdapter(recyclerViewVerticalCoin);


        return rootView;
    }

    int flag =0;


    public void call_market_api() {

        RequestQueue requestQueue = null;

        String url = "https:// json_url/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Market_url, null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }
                });

        requestQueue.add(jsonObjectRequest);

    }

    private void api_call(){
        try {
            JSONObject jsonBody = new JSONObject();
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, Market_url,jsonBody, response -> {
                try {
                    JSONObject jsonObject = new JSONObject(response+"");
                    Log.i("response->",jsonObject+"");
                    String s = jsonObject.getString("name");
                    Log.i("getas",s);
                    //pb_loading.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                // pb_loading.setVisibility(View.GONE);
                Log.i("asd","asd");
                error.printStackTrace();
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void refresh(){

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                Market_url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject student = response.getJSONObject(i);
                                // Get the current student (json object) data
                                mMarketCoinAdapter.addAddcoinsTickersModel(new CoinsTickersModel(
                                        student.getString("name"),
                                        student.getString("image"),
                                        student.getString("current_price")));

                                mCoinHorizAdapter.addcoinsHoriz(new CoinsTickersModel(
                                        student.getString("name"),
                                        student.getString("image"),
                                        student.getString("current_price")));

                            }
                            try {
                                getActivity().runOnUiThread(() -> {
                                    if (getActivity() != null)
                                        mMarketCoinAdapter.notifyDataIsChanged();
                                });

                                getActivity().runOnUiThread(() -> {
                                    if (getActivity() != null)
                                        mCoinHorizAdapter.notifyDataIsChanged();
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred

                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }




    /**public void refresh_data() {
        //clearSelection();
        //call_market_api();
        //mCoinHorizAdapter.clearList();
        // notifyDataSetChanged must run in main ui thread, if run in not ui thread, it will not update until manually scroll recyclerview

        try{
            (getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //mCoinHorizAdapter.notifyDataSetChanged();
                    call_market_api();
                    mCoinHorizAdapter.notifyDataIsChanged();
                    mMarketCoinAdapter.notifyDataIsChanged();
                    Log.i("sid","in datachanged");
                    mMarketCoinAdapter.clearList();
                    mMarketCoinAdapter.notifyDataIsChanged();
                    mMarketCoinAdapter.clearList();
                    if(flag == 1){
                        mMarketCoinAdapter.clearList();
                    }

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }**/

    public void refresh_data_period(){ }


    private void initViews() {
        recyclerViewHorizCoin = (RecyclerView) rootView.findViewById(R.id.recycler_coin_horizontal);
        recyclerViewVerticalCoin = (RecyclerView) rootView.findViewById(R.id.recycler_market);
        //btnAddCurrency = (LinearLayout) rootView.findViewById(R.id.btn_add_new_currency);


    }
}