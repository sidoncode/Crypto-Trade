package com.cryptotrade.AdapterPackage;
/**
 * all required libraries importation goes here
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryptotrade.AdapterPackage.Models.CoinsTickersModel;
import com.cryptotrade.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;



public class CoinHorizAdapter{

    protected final int ANCHOR_UID = Integer.MAX_VALUE;
    private static final int MAX_MSG_COUNT = 20;
    private Context mContext;
    private LayoutInflater mInflater;
    private RecyclerView mRecyclerView;
    private CoinHorizAdapter.MyAdapter mAdapter;
    private List<CoinsTickersModel> mBookingList;
    private ImageView mImageView;


    public CoinHorizAdapter(RecyclerView listView) {
        mRecyclerView = listView;
        mContext = mRecyclerView.getContext();
        mInflater = LayoutInflater.from(mContext);
        mBookingList = new ArrayList<CoinsTickersModel>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));

        mAdapter = new CoinHorizAdapter.MyAdapter(mBookingList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void notifyDataIsChanged() {

        mAdapter.notifyDataSetChanged();
    }

    public void addcoinsHoriz(CoinsTickersModel booking) {

        mBookingList.add(booking);

    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<CoinHorizAdapter.MyViewHolder> {
        private List<CoinsTickersModel> mBookingList;

        MyAdapter(List<CoinsTickersModel> list) {
            mBookingList = list;
        }

        @SuppressLint("InflateParams")
        @Override
        public CoinHorizAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CoinHorizAdapter.MyViewHolder(mInflater.inflate(R.layout.layout_coin_horiz_row, null));
        }


        @Override
        public void onBindViewHolder(CoinHorizAdapter.MyViewHolder holder, int position) {

            //CoinsTickersModel b = mBookingList.get(position);
            CoinsTickersModel coinsTickersModel = mBookingList.get(position);

            //((TextView)holder.itemView.findViewById(R.id.cname_tv)).setText("₹ "+coinsTickersModel.getSell());

            ((TextView)holder.itemView.findViewById(R.id.cname_tv)).setText(coinsTickersModel.getId());
            //((TextView)holder.itemView.findViewById(R.id.tv_amount)).setText(coinsTickersModel.getSell());
            ((TextView)holder.itemView.findViewById(R.id.tv_amount_inr)).setText("₹ "+coinsTickersModel.getCurrent_price());


        }
        @Override
        public int getItemCount() {
            return mBookingList.size();
        }
    }

    public void clearList(){
        mBookingList.clear();
    }
}



/**public class CoinHorizAdapter extends RecyclerView.Adapter<CoinHorizAdapter.ViewHolder> {
    ArrayList<String> activityList;

    Activity activity;

    public CoinHorizAdapter(ArrayList<String> activityList, Activity activity) {
        this.activityList = activityList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.layout_coin_horiz_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {


        ViewHolder holder = (ViewHolder) viewHolder;

        //holder.tvAmount.setText("0.0000014 BTC");

    }


    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAmount;

        public ViewHolder(View itemView) {
            super(itemView);

            tvAmount = (TextView) itemView.findViewById(R.id.tv_amount);
        }
    }
}
**/
