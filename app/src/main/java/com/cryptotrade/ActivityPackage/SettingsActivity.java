package com.cryptotrade.ActivityPackage;
/**
 * all required libraries imported here
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageButton;

import com.cryptotrade.R;
import com.cryptotrade.AdapterPackage.SettingsAdapter;
import com.cryptotrade.UtilPackage.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    /**
     * field instances of views and variables goes here
     */
    RecyclerView recyclerView;
    ItemTouchHelper mItemTouchHelper;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        /**
         * initializing views
         */
        initViews();

        /**
         * creating linear layout manager and setting to recycler view list
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SettingsActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);


        /**
         * creating item touch helper for item reorder and passing the adapter to it
         * creating demo data and setting to recycler view with adapter
         */
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("BTC");
        }
        SettingsAdapter adapter = new SettingsAdapter(arrayList, SettingsActivity.this);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);


        /**
         * click listener of back button
         */
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * finishing this screen
                 */
                SettingsActivity.super.onBackPressed();
            }
        });

    }

    /**
     * initialize views
     */

    private void initViews() {
        /**
         * type casting all views
         */
        recyclerView = (RecyclerView) findViewById(R.id.recycler_settings);
        btnBack = (ImageButton) findViewById(R.id.btn_back);

    }
}
