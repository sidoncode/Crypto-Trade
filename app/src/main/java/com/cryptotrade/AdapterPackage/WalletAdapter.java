package com.cryptocurrencybestrate.ethereum.AdapterPackage;
/**
 * all required libraries importation goes here
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cryptocurrencybestrate.ethereum.R;

import com.cryptocurrencybestrate.ethereum.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;


public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {
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

    public WalletAdapter(ArrayList<String> activityList, Activity activity) {
        this.activityList = activityList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * creating view of layout  row and returning
         */
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_recycler_wallet_row, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        /**
         * type casting view holder to the item view holder
         */
        final ViewHolder holder = (ViewHolder) viewHolder;
        /**
         * click listener of row
         */
        holder.recyclerWalletRowClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * checking if expandable layout is exapanded or not
                 * if expanded then collapse
                 * else expand
                 */
                if (holder.expandableLayout.isExpanded()) {
                    holder.expandableLayout.collapse(true);
                } else {
                    holder.expandableLayout.expand(true);
                }
            }
        });
        /**
         * setting layout manager to the recycler view
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        holder.recyclerViewHistory.setLayoutManager(linearLayoutManager);

/**
 * demo data for recycler view
 */
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            arrayList.add("0.00005" + i);
        }
        /**
         * setting adapter with demo data to the recycler view
         */
        holder.recyclerViewHistory.setAdapter(new DepositHistoryAdapter(arrayList, activity));
    }

    /**
     * returning total item count
     * @return
     */
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

        ExpandableLayout expandableLayout;
        RelativeLayout recyclerWalletRowClick;
        RecyclerView recyclerViewHistory;

        public ViewHolder(View itemView) {
            super(itemView);

            /**
             *type casting of all vies
             */
            expandableLayout = (ExpandableLayout) itemView.findViewById(R.id.expandable_layout);
            recyclerWalletRowClick = (RelativeLayout) itemView.findViewById(R.id.recycler_wallet_row_click);
            recyclerViewHistory = (RecyclerView) itemView.findViewById(R.id.recycler_deposit_history);
        }
    }
}
