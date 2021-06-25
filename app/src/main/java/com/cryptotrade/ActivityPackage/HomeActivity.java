package com.cryptocurrencybestrate.ethereum.ActivityPackage;
/**
 * All required libraries imported here
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cryptocurrencybestrate.ethereum.AdapterPackage.CoinHorizAdapter;
import com.cryptocurrencybestrate.ethereum.AdapterPackage.MarketCoinAdapter;
import com.cryptocurrencybestrate.ethereum.AdapterPackage.Models.CoinsTickersModel;
import com.cryptocurrencybestrate.ethereum.FragmentPackage.MarketFragment;
import com.cryptocurrencybestrate.ethereum.FragmentPackage.WalletFragment;
import com.cryptocurrencybestrate.ethereum.UtilPackage.ShiftingRemover;
import com.cryptocurrencybestrate.ethereum.R;
import com.cryptocurrencybestrate.ethereum.UtilPackage.Utility;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


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
    InterstitialAd mInterstitialAd;
    Spinner mSpinner;
    String s;
    TextView username_tv;
    private CircleImageView image_profile;


    FragmentManager fragmentManager = getSupportFragmentManager();
    final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    final MarketFragment myFragment = new MarketFragment();

    MarketFragment marketFragment1 = new MarketFragment();


    private String user_name =null;
    private Uri user_pic = null;
    LinearLayout rateus;
    LinearLayout shareapp;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * setting up the home screen layout to this screen
         */
        setContentView(R.layout.activity_home);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        try {
            user= mAuth.getCurrentUser();
        }catch (Exception e){
            e.printStackTrace();
        }

        Bundle bundle = new Bundle();
        bundle.putString("edttext", "From Activity");
        MarketFragment fragobj = new MarketFragment();
        fragobj.setArguments(bundle);

        username_tv = findViewById(R.id.username_tv);


        FirebaseApp.initializeApp(HomeActivity.this);

        //user_pic = Uri.parse(getIntent().getStringExtra("user_pic"));
        try {
            user_name = getIntent().getStringExtra("user_name");
            username_tv.setText(user_name);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (user_name.isEmpty()) {
                Log.i("user_name", "is empty");
            }
            if (!user_name.isEmpty()) {
                username_tv.setText(user_name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (user_name.isEmpty()) {
                if (Utility.getUserFirstName(HomeActivity.this).equals("")) {
                    username_tv.setText("");
                } else {
                    username_tv.setText(Utility.getUserFirstName(HomeActivity.this));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(username_tv.getText().equals("")){
                username_tv.setText(user.getDisplayName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }



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
                Bundle bundle = new Bundle();
                bundle.putString("edttext", "From Activity");
                MarketFragment fragobj = new MarketFragment();
                fragobj.setArguments(bundle);

                fragmentTransaction.add(R.id.fragment_container, myFragment).commit();

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
                //navigation.setSelectedItemId(R.id.navigation_exchange);
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

                AdRequest adRequest = new AdRequest.Builder().build();

                InterstitialAd.load(HomeActivity.this,"ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("sidd", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("sidd", loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });

                navigation.setSelectedItemId(R.id.navigation_discover);
            }
        });


        /**
         * click listener of logout button
         */
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    Utility.setUserMobile(HomeActivity.this,"");
                    Utility.setUserLasstName(HomeActivity.this,"");
                    Utility.setUserFirstName(HomeActivity.this,"");
                }catch (Exception e){
                    e.printStackTrace();
                }

                try{
                    Utility.g_setUserFirstName(HomeActivity.this,"");
                    Utility.g_setUserLasstName(HomeActivity.this,"");
                    Utility.g_setUserMobile(HomeActivity.this,"");
                    FirebaseAuth.getInstance().signOut();
                }catch (Exception e){
                    e.printStackTrace();
                }
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
                //navigation.setSelectedItemId(R.id.navigation_wallet);

            }
        });


        /**
         * click listener of settings button
         */

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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

                    tvTitle.setText("Live Coin Rates");
                    btnSettings.clearAnimation();
                    btnSettings.animate().alpha(1);
                    Bundle bundle = new Bundle();
                    bundle.putString("edttext", "From Activity");
                    MarketFragment fragobj = new MarketFragment();
                    fragobj.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MarketFragment()).commitAllowingStateLoss();

                    return true;
                case R.id.navigation_wallet:
                    /**
                     * opening wallet screen
                     */
                    /**

                    //tvTitle.setText("Paper Trading");
                    btnSettings.clearAnimation();
                    btnSettings.animate().alpha(0);**/
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WalletFragment()).commitAllowingStateLoss();**/
                    Toast.makeText(HomeActivity.this,"Coming Soon",Toast.LENGTH_LONG).show();

                    return true;

                case R.id.navigation_exchange:

                    Toast.makeText(HomeActivity.this,"Coming Soon",Toast.LENGTH_LONG).show();
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
        image_profile = findViewById(R.id.image_profile);
        rateus = findViewById(R.id.rateus);
        shareapp = findViewById(R.id.shareapp);


        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+HomeActivity.this.getPackageName())));
            }
        });


        shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int applicationNameId = HomeActivity.this.getApplicationInfo().labelRes;
                final String appPackageName = HomeActivity.this.getPackageName();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Crypto Coins");
                String text = "Install This Application:" + "\n" + "Get Daily Live Prices of Crypto Coins";
                String link = "https://play.google.com/store/apps/details?id=" + appPackageName;
                i.putExtra(Intent.EXTRA_TEXT, text + " " + link);
                startActivity(Intent.createChooser(i, "Share link:"));
            }
        });


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


    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
        alertDialog.setTitle("AlertDialog");

        String[] items = {"USD","INR","YEN","EURO","ROUB"};
        int checkedItem = 1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        try{
                            Bundle bundle = new Bundle();
                            bundle.putString("edttext", "USD");
                            MarketFragment myFragment = new MarketFragment();
                            myFragment.setArguments(bundle);
                            fragmentTransaction.add(R.id.fragment_container, myFragment).commit();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        Toast.makeText(HomeActivity.this, "Clicked on java", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        MarketFragment marketFragment2 = new MarketFragment();
                        marketFragment2.refresh("INR");
                        Toast.makeText(HomeActivity.this, "Clicked on android", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        MarketFragment marketFragment3 = new MarketFragment();
                        marketFragment3.refresh("YEN");
                        Toast.makeText(HomeActivity.this, "Clicked on Data Structures", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(HomeActivity.this, "Clicked on HTML", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        MarketFragment marketFragment4 = new MarketFragment();
                        marketFragment4.refresh("EURO");
                        Toast.makeText(HomeActivity.this, "Clicked on CSS", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.show();
    }



}
