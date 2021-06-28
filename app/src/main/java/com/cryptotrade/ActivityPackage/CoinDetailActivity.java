package com.cryptocurrencybestrate.ethereum.ActivityPackage;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptocurrencybestrate.ethereum.R;
import com.github.mikephil.charting.charts.LineChart;

/**
 * All required libraries imported here
 */


public class CoinDetailActivity extends AppCompatActivity{


    RecyclerView recyclerView;
    ImageButton btnBack;
    ImageButton btnCross;
    LineChart chart;

    TextView marketcaprank_tv,marketcap_tv,fullydilutedvalidation_tv,tradingvolume_tv,high_tv,low_tv,availablesupply_tv,totalsupply_tv,
            alltimehigh_tv;

    TextView alltimelowdata_tv;



    String marketcaprank = null;
    String marketcap = null;
    String fullydilutedvalidation = null;
    String tradingvolume = null;
    String high=null;
    String low=null;
    String availablesupply=null;
    String totalsupply=null;
    String  alltimehigh=null;
    String sincealltimehigh=null;
    String current_price = null;
    String price_change_24h = null;
    String maxsupply = null;
    String totalvol = null;
    String ath = null;
    String atl = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_coin_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_coin_horizontal);
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnCross = (ImageButton) findViewById(R.id.btn_cross);
        chart = (LineChart) findViewById(R.id.line_chirt);
        setSupportActionBar(toolbar);

        marketcap_tv =findViewById(R.id.marketcap_tv);
        marketcaprank_tv =findViewById(R.id.marketcaprank_tv);
        fullydilutedvalidation_tv =findViewById(R.id.fullydilutedvalidation_tv);
        tradingvolume_tv =findViewById(R.id.tradingvolume_tv);
        high_tv =findViewById(R.id.high_tv);
        low_tv =findViewById(R.id.low_tv);
        totalsupply_tv =findViewById(R.id.totalsupply_tv);

        alltimehigh_tv =findViewById(R.id.alltimehigh_tv);
        alltimelowdata_tv = findViewById(R.id.alltimelowdata_tv);



        try{
             current_price = getIntent().getStringExtra("current_price");
             marketcap = getIntent().getStringExtra("market_cap");
             marketcaprank = getIntent().getStringExtra("market_cap_rank");
             fullydilutedvalidation = getIntent().getStringExtra("fully_diluted_valuation");
             totalvol=getIntent().getStringExtra("total_volume");
             high=getIntent().getStringExtra("high_24h");
             low=getIntent().getStringExtra("low_24h");
             price_change_24h=getIntent().getStringExtra("price_change_24h");
             totalsupply=getIntent().getStringExtra("total_supply");
             maxsupply=getIntent().getStringExtra("max_supply");
             ath=getIntent().getStringExtra("ath");
             atl=getIntent().getStringExtra("atl");

            Log.i("siddd1",current_price);
            Log.i("siddd2",marketcap);
            Log.i("siddd3",marketcaprank);
            Log.i("siddd4",fullydilutedvalidation);
            Log.i("siddd5",totalsupply);
            Log.i("siddd6",high);
            Log.i("siddd7",low);
            Log.i("siddd8",price_change_24h);
            Log.i("siddd9",totalvol);
            Log.i("siddd10",maxsupply);


        }catch (Exception e){
            e.printStackTrace();

        }

        set_vals();


    }
    public void set_vals(){

        marketcaprank_tv.setText(marketcaprank);
        marketcap_tv.setText(marketcap);
        fullydilutedvalidation_tv.setText(fullydilutedvalidation);
        tradingvolume_tv.setText(totalvol);
        high_tv.setText(high);
        low_tv.setText(low);
        totalsupply_tv.setText(totalsupply);
        alltimehigh_tv.setText(ath);
        alltimelowdata_tv.setText(atl);

    }

}




/**
public class CoinDetailActivity extends AppCompatActivity {

    LineChart chart;
    RecyclerView recyclerView;
    ImageButton btnBack, btnCross;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_coin_detail);
        initViews();

        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("0.00005" + i);
        }

        recyclerView.setAdapter(new CoinDetailHorizAdapter(arrayList, CoinDetailActivity.this));


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CoinDetailActivity.super.onBackPressed();
            }
        });


        btnCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CoinDetailActivity.super.onBackPressed();
            }
        });
    }

    public void linechartModification() {


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


        ArrayList<Entry> candleEntries = new ArrayList<Entry>();
        candleEntries.add(new Entry(12f, 12));
        candleEntries.add(new Entry(5f, 11));
        candleEntries.add(new Entry(7f, 10));
        candleEntries.add(new Entry(9f, 13));
        candleEntries.add(new Entry(13f, 10));
        candleEntries.add(new Entry(15f, 19));
        candleEntries.add(new Entry(17f, 14));


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
        set.setFillDrawable(ContextCompat.getDrawable(CoinDetailActivity.this, R.drawable.chart_gred));
        LineData candleData = new LineData(set);
        //   candleData.setValueTextColor(Color.parseColor("#C8D5F8"));

        chart.setData(candleData);
        chart.animateY(1000, Easing.EasingOption.EaseInCubic);
        chart.animateX(1000, Easing.EasingOption.EaseInCubic);
        chart.setViewPortOffsets(0f, 0f, 0f, 0f);
        // myMarkerImage.setOffset(10f, 13f);


    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_coin_horizontal);
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnCross = (ImageButton) findViewById(R.id.btn_cross);
        chart = (LineChart) findViewById(R.id.line_chirt);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#202B3F"));
        }



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CoinDetailActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        linechartModification();

    }
}
**/
