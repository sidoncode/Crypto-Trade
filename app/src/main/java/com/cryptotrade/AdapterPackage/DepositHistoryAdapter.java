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



public class DepositHistoryAdapter extends RecyclerView.Adapter<DepositHistoryAdapter.ViewHolder> {
    /**
     * Field instance of all views and variables
     */
    ArrayList<String> depositHistoryList;

    Activity activity;

    /**
     * construtctor for getting activity list and activity context
     *
     * @param
     */

    public DepositHistoryAdapter(ArrayList<String> depositHistoryList, Activity activity) {
        this.depositHistoryList = depositHistoryList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * creating view of layout activity row and returning
         */
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_deposit_history_row, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        /**
         * type casting view holder to the item view holder
         */
        final ViewHolder holder = (ViewHolder) viewHolder;
        /**
         * setting text to the views
         */
        holder.tvAmount.setText("+4.2134 USD");

    }


    @Override
    public int getItemCount() {
        return depositHistoryList.size();
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
