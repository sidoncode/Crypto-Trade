package com.cryptotrade.AdapterPackage;
/**
 * all required libraries importation goes here
 */

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.cryptotrade.ActivityPackage.DiscoverDetailsActivity;
import com.cryptotrade.R;

import java.util.ArrayList;




public class EventHorizAdapter extends RecyclerView.Adapter<EventHorizAdapter.ViewHolder> {
    /**
     * Field instance of all views and variables
     */
    ArrayList<String> activityList;

    Activity activity;

    /**
     * construtctor for getting activity list and activity context
     *
     * @param activityList
     */

    public EventHorizAdapter(ArrayList<String> activityList, Activity activity) {
        this.activityList = activityList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * creating view of layout activity row and returning
         */
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_event_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        /**
         * type casting view holder to the item view holder
         */
        final ViewHolder holder = (ViewHolder) viewHolder;
        /**
         * setting image to the image view
         */
        Glide.with(activity).load(R.drawable.demo_news).into(holder.imageview);
        /**
         * click listener of row
         */
        holder.line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * starting discover detail activity screen
                 */
                ActivityOptionsCompat activityOptionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(activity,holder.line1,"dis");
                activity.startActivity(new Intent(activity,DiscoverDetailsActivity.class),activityOptionsCompat.toBundle());
            }
        });

    }


    @Override
    public int getItemCount() {
        return activityList.size();
    }


    /**
     * custom view holder class for item view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
LinearLayout line1;
        /**
         * field instance of all views and variables
         */
        ImageView imageview;

        public ViewHolder(View itemView) {
            super(itemView);

            /**
             *type casting of all vies
             */
            imageview = (ImageView) itemView.findViewById(R.id.imageview);
            line1=(LinearLayout)itemView.findViewById(R.id.line1);
        }
    }
}
