package com.cryptocurrencybestrate.ethereum.ActivityPackage;
/**
 * all required libraries imported here
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptocurrencybestrate.ethereum.AdapterPackage.SettingsAdapter;
import com.cryptocurrencybestrate.ethereum.FragmentPackage.MarketFragment;
import com.cryptocurrencybestrate.ethereum.UtilPackage.SimpleItemTouchHelperCallback;
import com.cryptocurrencybestrate.ethereum.R;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    /**
     * field instances of views and variables goes here
     */
    RecyclerView recyclerView;
    ItemTouchHelper mItemTouchHelper;
    ImageButton btnBack;


    SettingsAdapter adapter;

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
        adapter = new SettingsAdapter(arrayList, SettingsActivity.this);
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
                //SettingsActivity.super.onBackPressed();
                /**Intent intent = new Intent(SettingsActivity.this, MarketFragment.class);
                intent.putExtra("val","INR");
                startActivity(intent);
                Log.i("val","val");**/
            }
        });



    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(SettingsActivity.this, HomeActivity.class);
        intent.putExtra("val","INR");
        startActivity(intent);
        Log.i("val","val");
        super.onBackPressed();  // optional depending on your needs
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
