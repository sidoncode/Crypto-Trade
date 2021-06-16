package com.cryptotrade.ActivityPackage;
/**
 * All required libraries imported here
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.cryptotrade.R;

public class DiscoverDetailsActivity extends AppCompatActivity {
    /**
     * Field instances of all views and variables goes here
     */
    ImageButton btnBack;
    Button btnBottomBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * setting up the discover details screen
         */
        setContentView(R.layout.activity_discover_details);
initViews();

        /**
         * click listener of back button
         */

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * finishing this screen
                 */
                DiscoverDetailsActivity.super.onBackPressed();
            }
        });

        btnBottomBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * finishing this screen
                 */
                DiscoverDetailsActivity.super.onBackPressed();
            }
        });

    }

    /**
     * initialising all views
     */
    private void initViews() {
        /**
         * type casting all views
         */
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBottomBack = (Button) findViewById(R.id.btn_backs);
    }



}
