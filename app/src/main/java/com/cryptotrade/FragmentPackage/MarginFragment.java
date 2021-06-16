package com.cryptotrade.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.cryptotrade.R;

import java.util.ArrayList;


public class MarginFragment extends Fragment {
    /**
     * field instance of all views
     */
    View rootView;
    CandleStickChart candleStickChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * assigning the layout of margin and type casting of all child views
         */
        rootView = inflater.inflate(R.layout.layout_margin, container, false);
/**
 * initializing views
 */
        initViews();
        /*
        calling candle modification method
         */
        candleModification();
        return rootView;
    }

    /**
     * making ready candle light graph
     */
    public void candleModification() {
        /**
         * customising candle light graph
         */
        candleStickChart.setBackgroundColor(Color.TRANSPARENT); // use your bg color
        Description description = new Description();
        description.setText("");
        candleStickChart.setDescription(description);
        candleStickChart.setDrawGridBackground(false);
        candleStickChart.setDrawBorders(false);
        candleStickChart.setAutoScaleMinMaxEnabled(true);
        // remove axis
        YAxis leftAxis = candleStickChart.getAxisLeft();
        leftAxis.setEnabled(false);
        leftAxis.setTextColor(Color.parseColor("#C8D5F8"));
        YAxis rightAxis = candleStickChart.getAxisRight();
        rightAxis.setEnabled(false);
        XAxis xAxis = candleStickChart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setTextColor(Color.parseColor("#C8D5F8"));
        // hide legend
        Legend legend = candleStickChart.getLegend();
        legend.setEnabled(false);
        /**
         * adding demo data to the candle light
         */
        ArrayList<CandleEntry> candleEntries = new ArrayList<CandleEntry>();

        candleEntries.add(new CandleEntry(0,
                4.62f, 2.02f, 2.70f, 4.13f));
        candleEntries.add(new CandleEntry(1,
                5.50f, 2.70f, 3.35f, 4.96f));
        candleEntries.add(new CandleEntry(2,
                8.12f, 3.43f, 5.65f, 2.13f));
        candleEntries.add(new CandleEntry(3,
                6.62f, 2.16f, 5.70f, 6.43f));
        candleEntries.add(new CandleEntry(4,
                9.53f, 6.94f, 3.54f, 2.43f));
        candleEntries.add(new CandleEntry(5,
                7.54f, 4.23f, 5.54f, 3.23f));
        candleEntries.add(new CandleEntry(6,
                4.54f, 3.23f, 2.54f, 4.23f));
        candleStickChart.animateY(500);
        CandleDataSet cds = new CandleDataSet(candleEntries, "");
        cds.setColor(Color.parseColor("#36AE67"));
        cds.setShadowColor(Color.parseColor("#36AE67"));
        cds.setShadowWidth(0.7f);
        cds.setDecreasingColor(Color.parseColor("#36AE67"));
        cds.setDecreasingPaintStyle(Paint.Style.FILL);
        cds.setIncreasingColor(Color.parseColor("#C7494A"));
        cds.setIncreasingPaintStyle(Paint.Style.FILL);
        cds.setNeutralColor(Color.parseColor("#36AE67"));
        cds.setDrawValues(false);
        CandleData cd = new CandleData(cds);
        /**
         * setting data to the candle light graph
         */
        candleStickChart.setData(cd);
        candleStickChart.invalidate();
    }

    /**
     * type casting all views
     */
    private void initViews() {
        candleStickChart = (CandleStickChart) rootView.findViewById(R.id.candle_chirt);
    }
}
