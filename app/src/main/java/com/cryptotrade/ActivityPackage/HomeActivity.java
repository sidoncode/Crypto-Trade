package com.cryptotrade.ActivityPackage;
/**
 * All required libraries imported here
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cryptotrade.FragmentPackage.ExchangeFragment;
import com.cryptotrade.FragmentPackage.MarketFragment;
import com.cryptotrade.R;
import com.cryptotrade.UtilPackage.ShiftingRemover;
import com.cryptotrade.FragmentPackage.WalletFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    /**
     * Field instances of all views and variables
     */
    ImageButton btnham;
    LinearLayout btnMarket, btnDiscover, btnWallet;
    ImageButton btnSettings;
    BottomNavigationView navigation;
    TextView tvTitle;
    LinearLayout btnExchange;
    LinearLayout linearLayoutToolbar;
    LinearLayout btnLogout;

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * setting up the home screen layout to this screen
         */
        setContentView(R.layout.activity_home);

/**
 * initializing views
 */
        initViews();

/**
 * click listener of market button
 */
        btnMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * closing drawer if it is open
                 */
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                /**
                 * opening market screen
                 */
                navigation.setSelectedItemId(R.id.navigation_market);
            }
        });



        /**
         * click listener of exchange button
         */

        btnExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * closing drawer if it is open
                 */
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                /**
                 * opening exchange screen
                 */
                navigation.setSelectedItemId(R.id.navigation_exchange);
            }
        });


        /**
         * click listener of ham button
         */
        btnham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * closing drawer if it is open
                 */
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    /**
                     * else opening drawer
                     */
                    drawer.openDrawer(GravityCompat.START);
                }

            }
        });



        /**
         * click listener of discover button
         */

        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * closing drawer if it is open
                 */
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                /**
                 *  opening discover screen
                 */
                navigation.setSelectedItemId(R.id.navigation_discover);
            }
        });


        /**
         * click listener of logout button
         */
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * starting login activity screen
                 */
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                /**
                 * finishing this activity
                 */
                finish();
            }
        });

        /**
         * click listener of wallet button
         */
        btnWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * closing drawer if it is open
                 */
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                /**
                 * opening wallet screen
                 */
                navigation.setSelectedItemId(R.id.navigation_wallet);
            }
        });


        /**
         * click listener of settings button
         */

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * starting settings screen
                 */
                //ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this, linearLayoutToolbar, "toolbar");
                //startActivity(new Intent(HomeActivity.this, SettingsActivity.class), compat.toBundle());

            }
        });


    }

    /**
     * on back pressed
     */

    @Override
    public void onBackPressed() {
        /**
         * closing drawer if it is opened other wise finishing this screen
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * bottom navigation drawer item selected
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                /**
                 * opening discover screen
                 */
                case R.id.navigation_discover:
                    /**
                     * setting title and clearing the animation of settings icon setting alpha 0(non visible)
                     */
                    /**tvTitle.setText("Discover");
                    btnSettings.clearAnimation();
                    btnSettings.animate().alpha(0);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DiscoverFragment()).commitAllowingStateLoss();**/
                    startActivity(new Intent(HomeActivity.this, NewsActivity.class));
                    return true;
                case R.id.navigation_market:
/**
 * opening market screen
 */    /**
                 * setting title and clearing the animation of settings icon setting alpha 1(visible)
                 */
                    tvTitle.setText("Market");
                    btnSettings.clearAnimation();
                    btnSettings.animate().alpha(1);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MarketFragment()).commitAllowingStateLoss();

                    return true;
                case R.id.navigation_wallet:
                    /**
                     * opening wallet screen
                     */
                    /**tvTitle.setText("Wallet");
                    btnSettings.clearAnimation();
                    btnSettings.animate().alpha(0);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WalletFragment()).commitAllowingStateLoss();**/


                    return true;

                case R.id.navigation_exchange:
                    /**
                     * opening exchange screen
                     */
                    tvTitle.setText("Exchange");
                    btnSettings.clearAnimation();
                    btnSettings.animate().alpha(0);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ExchangeFragment()).commitAllowingStateLoss();
                    //startActivity(new Intent(HomeActivity.this,ExchangeFragment.class));

                    return true;
            }
            return false;
        }

    };

    /**
     * initializing all views
     */
    private void initViews() {
        /**
         * type casting all views and setting default data
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        btnham = (ImageButton) findViewById(R.id.btn_ham);
        btnMarket = (LinearLayout) findViewById(R.id.btn_market);
        btnDiscover = (LinearLayout) findViewById(R.id.btn_discover);
        btnWallet = (LinearLayout) findViewById(R.id.btn_wallet);
        btnExchange = (LinearLayout) findViewById(R.id.btn_exchange);
        btnSettings = (ImageButton) findViewById(R.id.btn_settings);
        linearLayoutToolbar = (LinearLayout) findViewById(R.id.linear_toolbar);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnLogout = (LinearLayout) findViewById(R.id.btn_logout);
        navigation = (BottomNavigationView) findViewById(R.id.bottom_nav);
        btnSettings.clearAnimation();
        btnSettings.animate().alpha(0);

        /**
         * removing bottom navigation view's shifting animation
         */
        ShiftingRemover.removeShiftMode(navigation);
        /**
         * setting listener of bottom navigation view's item selection
         */
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        /**
         * setting default to market
         */
        navigation.setSelectedItemId(R.id.navigation_market);


        /**private void api_call(){
            try {
                JSONObject jsonBody = new JSONObject();
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, "",jsonBody, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response+"");
                        Log.i("response->",jsonObject+"");
                        String s = jsonObject.getString("name");
                        Log.i("getas",s);
                        //pb_loading.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    // pb_loading.setVisibility(View.GONE);
                    error.printStackTrace();
                });

            }catch (Exception e){
                e.printStackTrace();
            }

        }**/

    }

}
