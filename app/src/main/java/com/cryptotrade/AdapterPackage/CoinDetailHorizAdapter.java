package com.cryptotrade.AdapterPackage;
/**
 * all required libraries importation goes here
 */

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cryptotrade.R;

import java.util.ArrayList;




public class CoinDetailHorizAdapter extends RecyclerView.Adapter<CoinDetailHorizAdapter.ViewHolder> {
    /**
     * Field instance of all views and variables
     */
    ArrayList<String> activityList;

    Activity activity;

    /**
     * construtctor for getting  list and activity context
     *
     * @param activityList
     */

    public CoinDetailHorizAdapter(ArrayList<String> activityList, Activity activity) {
        this.activityList = activityList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * creating view of layout  row and returning
         */
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_detail_coin_horiz_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        /**
         * type casting view holder to the item view holder
         */
        ViewHolder holder = (ViewHolder) viewHolder;
        /**
         * setting text to the views
         */
        holder.tvAmount.setText("$0.0000014");

    }


    @Override
    public int getItemCount() {
        return activityList.size();
    }


    /**
     * custom view holder class for item view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * field instance of all views and variables
         */
        TextView tvAmount;

        public ViewHolder(View itemView) {
            super(itemView);

            /**
             *type casting of all vies
             */
            tvAmount = (TextView) itemView.findViewById(R.id.tv_amount);
        }
    }
}
