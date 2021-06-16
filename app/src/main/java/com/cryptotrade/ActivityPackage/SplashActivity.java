package com.cryptotrade.ActivityPackage;
/**
 * All required libraries imported here
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.cryptotrade.R;

public class SplashActivity extends AppCompatActivity {
    /**
     * field instances of all views and variables goes here
     */
    ImageView imageViewLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /***
         * setting this to full screen and hiding the status bar
         */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**
         * initialising views
         */
      initViews();
        /**
         * loading gif progress
         * handler for finish this splash screen after 4 seconds (4000 ms)
         * checking is logged in or not
         * if is logged in then it will redirect to main activity screen
         * other wise will redirect to login activity screen
         */
        GlideDrawableImageViewTarget mainImageViewTarget = new GlideDrawableImageViewTarget(imageViewLoading);
        Glide.with(this).load(R.drawable.cube).into(mainImageViewTarget);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 * finishing this activity
                 */
                finish();
                /**
                 * launching login activity screen
                 */
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }, 4000);//duration of staying into this launcher screen
    }

    /**
     * initializing all views
     */
    private void initViews(){
        /**
         * type casting all views
         */
        imageViewLoading = (ImageView) findViewById(R.id.loading_anim);
    }
}
