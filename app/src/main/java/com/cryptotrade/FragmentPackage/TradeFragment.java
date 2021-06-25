package com.cryptocurrencybestrate.ethereum.FragmentPackage;
/**
 * all required libraries imported here
 */

import androidx.fragment.app.Fragment;


public class TradeFragment extends Fragment {

}

/**
    View rootView;
    Spinner dropDownCoin, dropDownCurrency;
    RecyclerView recyclerViewSellOrder, recyclerViewBuyOrder;
    LineChart chart;

    TextView sum_inr; // after multiplying beside coin icon
    TextView totalinr; //total in the side text view
    EditText edt_totalcoin; // number of coins to be input //

    ArrayList<CoinsTickersModel> Coinsvals = new ArrayList<CoinsTickersModel>();

    Map mMap= new HashMap();

    Set set = mMap.entrySet();
    Iterator iterator;
    Button btn_login;
    ImageView img_coins;



    //ArrayList<String> demoCoinList = new ArrayList<String>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.layout_trade, container, false);
        //initViews();

        sum_inr = rootView.findViewById(R.id.sum_inr);
        totalinr = rootView.findViewById(R.id.totalinr);
        edt_totalcoin = rootView.findViewById(R.id.edt_totalcoin);
        img_coins = rootView.findViewById(R.id.img_coins);


        call_market_api();


        btn_login = rootView.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i("siddd",Coinsvals.get(0).getSell());
            }
        });

        linechartModification();


        return rootView;
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
        set.setFillDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.chart_gred));
        LineData candleData = new LineData(set);
        //   candleData.setValueTextColor(Color.parseColor("#C8D5F8"));
        chart.setData(candleData);
        chart.animateY(1000, Easing.EasingOption.EaseInCubic);
        chart.animateX(1000, Easing.EasingOption.EaseInCubic);
        chart.setViewPortOffsets(0f, 0f, 0f, 0f);
        // myMarkerImage.setOffset(10f, 13f);


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initViews() {

        call_market_api();

        chart = (LineChart) rootView.findViewById(R.id.line_chirt);
        dropDownCoin = (Spinner) rootView.findViewById(R.id.drop_down_coin);
        dropDownCurrency = (Spinner) rootView.findViewById(R.id.drop_down_currency);
        recyclerViewSellOrder = (RecyclerView) rootView.findViewById(R.id.recycler_sell_order);
        recyclerViewBuyOrder = (RecyclerView) rootView.findViewById(R.id.recycler_buy_order);

        ArrayList<String> demoCurrencyList = new ArrayList<String>();
        demoCurrencyList.add("INR");
        demoCurrencyList.add("USD");
        demoCurrencyList.add("EURO");
        demoCurrencyList.add("YIEN");


        Drawable image=(Drawable) getActivity().getResources().getDrawable(R.drawable.xpr);
        Drawable image1=(Drawable) getActivity().getResources().getDrawable(R.drawable.eth);
        Drawable image2=(Drawable) getActivity().getResources().getDrawable(R.drawable.btc);
        Drawable image3=(Drawable) getActivity().getResources().getDrawable(R.drawable.trx);
        Drawable image4=(Drawable) getActivity().getResources().getDrawable(R.drawable.eos);
        Drawable image5=(Drawable) getActivity().getResources().getDrawable(R.drawable.zil);
        Drawable image6=(Drawable) getActivity().getResources().getDrawable(R.drawable.bat);
        Drawable image7=(Drawable) getActivity().getResources().getDrawable(R.drawable.zrx);
        Drawable image8=(Drawable) getActivity().getResources().getDrawable(R.drawable.omg);
        Drawable image9=(Drawable) getActivity().getResources().getDrawable(R.drawable.iostt);
        Drawable image10=(Drawable) getActivity().getResources().getDrawable(R.drawable.dent);

        dropDownCurrency.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.layout_text, R.id.text, demoCurrencyList));





        int s = dropDownCoin.getSelectedItemPosition();
        Toast.makeText(getActivity(),String.valueOf(s),Toast.LENGTH_LONG).show();
        dropDownCoin.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.layout_text, R.id.text, demoCoinList));


        dropDownCoin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                int selectedSugg = parent.getSelectedItemPosition();
                Log.d("Suggestions ", String.valueOf(selectedSugg));

                if(selectedSugg == 0){
                    sum_inr.setText(Coinsvals.get(0).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs = Double.valueOf(Coinsvals.get(0).getSell());
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image2);
                }

                if(selectedSugg == 1){
                    Double rs = Double.valueOf(Coinsvals.get(1).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image);
                }

                if(selectedSugg == 2){
                    sum_inr.setText(Coinsvals.get(2).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs = Double.valueOf(Coinsvals.get(2).getSell());
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image1);
                }

                if(selectedSugg == 3){
                    sum_inr.setText(Coinsvals.get(3).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs = Double.valueOf(Coinsvals.get(3).getSell());
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image3);
                }

                if(selectedSugg == 4){
                    sum_inr.setText(Coinsvals.get(4).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs = Double.valueOf(Coinsvals.get(4).getSell());
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image4);
                }

                if(selectedSugg == 5){
                    sum_inr.setText(Coinsvals.get(5).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs = Double.valueOf(Coinsvals.get(5).getSell());
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image5);
                }

                if(selectedSugg == 6){
                    sum_inr.setText(Coinsvals.get(6).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs = Double.valueOf(Coinsvals.get(6).getSell());
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image6);
                }

                if(selectedSugg == 7){
                    sum_inr.setText(Coinsvals.get(7).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs = Double.valueOf(Coinsvals.get(7).getSell());
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image7);
                }

                if(selectedSugg == 8){
                    sum_inr.setText(Coinsvals.get(8).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs = Double.valueOf(Coinsvals.get(8).getSell());
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image8);
                }

                if(selectedSugg == 9){
                    sum_inr.setText(Coinsvals.get(9).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs = Double.valueOf(Coinsvals.get(9).getSell());
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image9);
                }

                if(selectedSugg == 10){
                    sum_inr.setText(Coinsvals.get(10).getSell());
                    if(edt_totalcoin.getText().equals("")){
                        Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                    }
                    Double rs = Double.valueOf(Coinsvals.get(10).getSell());
                    Double rs1 = Double.valueOf(edt_totalcoin.getText().toString());
                    Log.i("rs*rs1",String.valueOf(rs) + String.valueOf(rs1));
                    Double rss = rs * rs1;
                    //sum_inr.setText(String.valueOf(rss));
                    totalinr.setText(String.valueOf(rss));
                    img_coins.setBackground(image10);
                }

                if(edt_totalcoin.getText().equals("")){
                    Toast.makeText(getActivity(),"Please Select Number of Buying Coins",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        call_market_api();



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewBuyOrder.setLayoutManager(linearLayoutManager);


        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSellOrder.setLayoutManager(linearLayoutManager1);


        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("0.00005" + i);
        }


        recyclerViewSellOrder.setAdapter(new SellOrderAdapter(arrayList, getActivity()));
        recyclerViewBuyOrder.setAdapter(new BuyOrderAdapter(arrayList, getActivity()));

    }**/




    /**public void call_market_api() {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String Market_url = "https://api.wazirx.com/api/v2/tickers";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Market_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getActivity(),"response->"+ response,Toast.LENGTH_LONG).show();
                        Log.i("responset", response.toString());
                        try {
                            JSONObject resobj = new JSONObject(response);
                            Iterator<?> keys = resobj.keys();
                            while (keys.hasNext()) {
                                String key = (String) keys.next();
                                try {
                                    if (resobj.get(key) instanceof JSONObject) {
                                            JSONObject xx = new JSONObject(resobj.get(key).toString());
                                        Coinsvals.add(new CoinsTickersModel(
                                                    xx.getString("base_unit"),
                                                    xx.getString("quote_unit"),
                                                    xx.getString("low"),
                                                    xx.getString("high"),
                                                    xx.getString("last"),
                                                    xx.getString("type"),
                                                    xx.getString("volume"),
                                                    xx.getString("sell"),
                                                    xx.getString("buy"),
                                                    xx.getString("at"),
                                                    xx.getString("name")));
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            for (int i = 0;i<Coinsvals.size();i++){
                                demoCoinList.add(Coinsvals.get(i).getBase_unit().toUpperCase());
                                dropDownCoin.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.layout_text, R.id.text, demoCoinList));
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("response", "not working");
            }
        });
        queue.add(stringRequest);
    }
}**/
