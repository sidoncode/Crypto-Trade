package com.cryptocurrencybestrate.ethereum.AdapterPackage;
/**
 * all required libraries importation goes here
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptocurrencybestrate.ethereum.R;

import java.util.ArrayList;


public class SellOrderAdapter extends RecyclerView.Adapter<SellOrderAdapter.ViewHolder> {
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

    public SellOrderAdapter(ArrayList<String> activityList, Activity activity) {
        this.activityList = activityList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * creating view of layout  row and returning
         */
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_order_row, parent, false);


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
        holder.tvAmount.setText("0.00014");

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
