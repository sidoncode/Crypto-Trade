package com.cryptotrade.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.cryptotrade.R;
import com.cryptotrade.AdapterPackage.WalletAdapter;

import java.util.ArrayList;
import java.util.Collections;


public class WalletFragment extends Fragment {
    /**
     * field instances of views and variables goes here
     */
    View rootView;
    RecyclerView recyclerView;
    LineChart chart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_wallet, container, false);

        /**
         * initializing views
         */
        initViews();
        /*
        calling line chart modification
         */
        linechartModification();

        /**
         * creating linear layout manager and setting to recycler view list
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);


        /**
         * creating demo data and setting to recycler view with adapter
         */
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("0.00005" + i);
        }
        recyclerView.setAdapter(new WalletAdapter(arrayList, getActivity()));


        return rootView;
    }

    /**
     * setting line chart
     */
    public void linechartModification() {

        /**
         * chart background and color changing
         */
        chart.setBackgroundColor(Color.TRANSPARENT); // use your bg color
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);
        chart.setAutoScaleMinMaxEnabled(true);
        // remove axis
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setEnabled(false);//for enabling left velue increment
        leftAxis.setTextColor(Color.parseColor("#C8D5F8"));
        leftAxis.setEnabled(false);
        YAxis rightAxis = chart.getAxisRight(); // disabled right incrementes
        rightAxis.setEnabled(false);

        XAxis xAxis = chart.getXAxis();//// for top dates show
        xAxis.setEnabled(false);
        xAxis.setTextColor(Color.parseColor("#C8D5F8"));
        // hide legend
        Legend legend = chart.getLegend();
        legend.setEnabled(false); //for disableing legends text
        Description description = new Description();
        description.setText("");
        chart.setDescription(description);
        chart.invalidate();


        /**
         * setting demo data to chart
         */
        ArrayList<Entry> candleEntries = new ArrayList<Entry>();
        candleEntries.add(new Entry(12f, 12));
        candleEntries.add(new Entry(5f, 11));
        candleEntries.add(new Entry(7f, 10));
        candleEntries.add(new Entry(9f, 13));
        candleEntries.add(new Entry(13f, 10));
        candleEntries.add(new Entry(15f, 19));
        candleEntries.add(new Entry(17f, 14));

/**
 * sorting data
 */
        Collections.sort(candleEntries, new EntryXComparator());
        LineDataSet set = new LineDataSet(candleEntries, "");

        set.setColor(Color.parseColor("#EB7B22"));
        set.setDrawValues(false);
        set.setLineWidth(2f);

        set.setDrawCircles(false);
        set.setCubicIntensity(0.2f);
        set.setDrawHighlightIndicators(false);
        set.setDrawFilled(true);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setFillDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.chart_gred));
        LineData candleData = new LineData(set);
        //   candleData.setValueTextColor(Color.parseColor("#C8D5F8"));
        chart.setData(candleData);
        chart.animateY(1000, Easing.EasingOption.EaseInCubic);
        chart.animateX(1000, Easing.EasingOption.EaseInCubic);
        chart.setViewPortOffsets(0f, 0f, 0f, 0f);
        // myMarkerImage.setOffset(10f, 13f);


    }

    /**
     * initialize views
     */

    private void initViews() {
        /**
         * type casting all views
         */
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_wallet);
        chart = (LineChart) rootView.findViewById(R.id.line_chirt);

    }
}
