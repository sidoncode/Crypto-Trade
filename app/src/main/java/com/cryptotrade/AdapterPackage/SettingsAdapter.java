package com.cryptotrade.AdapterPackage;
/**
 * all required libraries importation goes here
 */

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cryptotrade.R;

import java.util.ArrayList;




public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> implements ItemTouchHelperAdapter{
    /**
     * Field instance of all views and variables
     */
    ArrayList<String> coinList;
    Activity activity;

    /**
     * construtctor for getting activity list and activity context
     *
     * @param
     */

    public SettingsAdapter(ArrayList<String> depositHistoryList, Activity activity) {
        this.coinList = depositHistoryList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * creating view of layout activity row and returning
         */
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_settings_row, parent, false);


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
        holder.tvCoinShortName.setText("BTC");
        holder.tvCoinFullName.setText("Bitcoin");
    }


    @Override
    public int getItemCount() {
        return coinList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        /**
         * re ordering row
         */
        String prev = coinList.remove(fromPosition);
        coinList.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {

    }


    /**
     * custom view holder class for item view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * field instance of all views and variables
         */
        TextView tvCoinShortName, tvCoinFullName;
        ImageButton btnReOrder;

        public ViewHolder(View itemView) {
            super(itemView);

            /**
             *type casting of all vies
             */
            tvCoinShortName = (TextView) itemView.findViewById(R.id.tv_coin_short_name);
            tvCoinFullName = (TextView) itemView.findViewById(R.id.tv_coin_full_name);
            btnReOrder = (ImageButton) itemView.findViewById(R.id.btn_re_arrange);
        }
    }


}
