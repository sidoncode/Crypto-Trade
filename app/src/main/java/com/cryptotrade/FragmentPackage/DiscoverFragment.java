package com.cryptotrade.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cryptotrade.AdapterPackage.EventHorizAdapter;
import com.cryptotrade.AdapterPackage.NewsAdapter;
import com.cryptotrade.R;
import com.cryptotrade.AdapterPackage.TrendHorizAdapter;

import java.util.ArrayList;


public class DiscoverFragment extends Fragment {
    /**
     * field instances of all views
     */
    View rootView;
    RecyclerView recyclerviewTrend, recyclcerviewEvent, recyclerViewNews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * setting discover layout to this fragment
         */
        rootView = inflater.inflate(R.layout.layout_discover, container, false);
        initViews();

        return rootView;
    }

    private void initViews() {
        /**
         * type casting alll views
         */
        recyclcerviewEvent = (RecyclerView) rootView.findViewById(R.id.recycler_discover_events);
        recyclerViewNews = (RecyclerView) rootView.findViewById(R.id.recycler_discover_news);
        recyclerviewTrend = (RecyclerView) rootView.findViewById(R.id.recycler_discover_trend);

/**
 * setting layout manager to the event recycler view
 */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclcerviewEvent.setLayoutManager(linearLayoutManager);


/**
 * setting layout manager to the trend recycler view
 */

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerviewTrend.setLayoutManager(linearLayoutManager1);


/**
 * setting layout manager to the news recycler view
 */
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewNews.setLayoutManager(linearLayoutManager2);

        /*
        setting up demo data
         */
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("0.00005" + i);
        }

        /**
         * setting demo data with adapter to the recycler views
         */
        //recyclerViewNews.setAdapter(new NewsAdapter(arrayList, getActivity()));
        recyclerviewTrend.setAdapter(new TrendHorizAdapter(arrayList, getActivity()));
        recyclcerviewEvent.setAdapter(new EventHorizAdapter(arrayList, getActivity()));


    }
}
