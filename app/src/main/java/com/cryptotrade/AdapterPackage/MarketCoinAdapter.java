package com.cryptotrade.AdapterPackage;
/**
 * all required libraries importation goes here
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cryptotrade.AdapterPackage.Models.CoinsTickersModel;
import com.cryptotrade.R;

import java.util.ArrayList;
import java.util.List;






public class MarketCoinAdapter{

    protected final int ANCHOR_UID = Integer.MAX_VALUE;
    private static final int MAX_MSG_COUNT = 20;
    private Context mContext;
    private LayoutInflater mInflater;
    private RecyclerView mRecyclerView;
    private MarketCoinAdapter.MyAdapter mAdapter;
    private List<CoinsTickersModel>mBookingList;
    private ImageView mImageView;


    public MarketCoinAdapter(RecyclerView listView) {
        mRecyclerView = listView;
        mContext = mRecyclerView.getContext();
        mInflater = LayoutInflater.from(mContext);
        mBookingList = new ArrayList<CoinsTickersModel>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mAdapter = new MarketCoinAdapter.MyAdapter(mBookingList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void notifyDataIsChanged() {

        mAdapter.notifyDataSetChanged();
    }

    public void addAddcoinsTickersModel(CoinsTickersModel booking) {

        mBookingList.add(booking);

    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MarketCoinAdapter.MyViewHolder> {
        private List<CoinsTickersModel> mBookingList;

        MyAdapter(List<CoinsTickersModel> list) {
            mBookingList = list;
        }

        @SuppressLint("InflateParams")
        @Override
        public MarketCoinAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MarketCoinAdapter.MyViewHolder(mInflater.inflate(R.layout.layout_market_row, null));
        }

        @Override
        public void onBindViewHolder(MarketCoinAdapter.MyViewHolder holder, int position) {

            //CoinsTickersModel b = mBookingList.get(position);
            CoinsTickersModel coinsTickersModel = mBookingList.get(position);

            ((TextView)holder.itemView.findViewById(R.id.tv_amount)).setText("₹ "+coinsTickersModel.getCurrent_price());
            ((TextView)holder.itemView.findViewById(R.id.name_tv)).setText(coinsTickersModel.getId());
            //((TextView)holder.itemView.findViewById(R.id.name_tv)).setText(coinsTickersModel.getImage());

            String Image_url = coinsTickersModel.getImage();


            Glide.with(mContext).load(coinsTickersModel.getImage()).into((ImageView) holder.itemView.findViewById(R.id.a1));




            /**Drawable image=(Drawable) mContext.getResources().getDrawable(R.drawable.xpr);
            Drawable image1=(Drawable) mContext.getResources().getDrawable(R.drawable.eth);
            Drawable image2=(Drawable) mContext.getResources().getDrawable(R.drawable.btc);
            Drawable image3=(Drawable) mContext.getResources().getDrawable(R.drawable.trx);
            Drawable image4=(Drawable) mContext.getResources().getDrawable(R.drawable.eos);
            Drawable image5=(Drawable) mContext.getResources().getDrawable(R.drawable.zil);
            Drawable image6=(Drawable) mContext.getResources().getDrawable(R.drawable.bat);
            Drawable image7=(Drawable) mContext.getResources().getDrawable(R.drawable.zrx);
            Drawable image8=(Drawable) mContext.getResources().getDrawable(R.drawable.omg);
            Drawable image9=(Drawable) mContext.getResources().getDrawable(R.drawable.iostt);
            Drawable image10=(Drawable) mContext.getResources().getDrawable(R.drawable.dent);

            mImageView  = holder.itemView.findViewById(R.id.a1);

            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("XRP/INR")){

                mImageView.setBackground(image);

            }

            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("BTC/INR")){

                mImageView.setBackground(image2);

            }

            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("ETH/INR")){

                mImageView.setBackground(image1);

            }
            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("TRX/INR")){

                mImageView.setBackground(image3);

            }
            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("EOS/INR")){

                mImageView.setBackground(image4);

            }
            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("ZIL/INR")){

                mImageView.setBackground(image5);

            }
            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("BAT/INR")){

                mImageView.setBackground(image6);

            }
            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("ZRX/INR")){

                mImageView.setBackground(image7);

            }
            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("OMG/INR")){

                mImageView.setBackground(image8);

            }
            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("IOST/INR")){

                mImageView.setBackground(image9);

            }
            if(((TextView) holder.itemView.findViewById(R.id.name_tv)).getText().equals("DENT/INR")){

                mImageView.setBackground(image10);

            }**/

        }
        @Override
        public int getItemCount() {
            return mBookingList.size();
        }
    }

    public void clearList(){
        mBookingList.clear();
    }

    public void refresh_vals(MarketCoinAdapter.MyViewHolder holder, int position){

        CoinsTickersModel coinsTickersModel = mBookingList.get(position);

        ((TextView)holder.itemView.findViewById(R.id.tv_amount)).setText("---");

    }


}