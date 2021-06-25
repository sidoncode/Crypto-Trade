package com.cryptocurrencybestrate.ethereum.ActivityPackage;
/**
 * All required libraries imported here
 */

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