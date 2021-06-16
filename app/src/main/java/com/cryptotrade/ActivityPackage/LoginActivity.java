package com.cryptotrade.ActivityPackage;
/**
 * all required libraries imported here
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cryptotrade.R;
import com.cryptotrade.FragmentPackage.SignupFragment;


public class LoginActivity extends AppCompatActivity {
    /**
     * Field instances of views and variables
     */
    Button btnSignup;
    public static FragmentManager manager;
    TextView tvForgotPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * setting up the content view of this screen
         */
        setContentView(R.layout.activity_login);
        /***
         * setting this to full screen and hiding the status bar
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#202B3F"));
        }

        /**
         * assigning the support fragment manager for future fragment replacement from any where
         */
        manager = getSupportFragmentManager();
        /**
         * Type casting of all views of login screen
         */
        initialiseViews();

/**
 * click listener of signup text which will redirect to the sign up screen
 */
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * replacing the login screen with sign up fragment screen here #fragment_container is the id of root layout of content main to
                 * change its content to fragment's content
                 */
                manager.beginTransaction().replace(R.id.fragment_container, new SignupFragment()).addToBackStack(null).commit();
            }
        });

        /**
         * click listener of forgot password text which will redirect to the forgot password screen
         */
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * replacing the login screen with forgot password fragment screen here #fragment_container is the id of root layout of content main to
                 * change its content to fragment's content
                 */
               // manager.beginTransaction().replace(R.id.fragment_container, new ForgotPasswordFragment()).addToBackStack(null).commit();
            }
        });


        /**
         * click listener for login button which will call api for login and if login succeed then will redirect to main screen
         */
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * starting the main screen
                 */
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));


                /**
                 * demo login API call on loigin button click
                 */
                //  apiCallForLogin("email","password");
            }
        });

    }

    /**
     * this method will type cast the all views of this screen
     */
    public void initialiseViews() {
        btnSignup = (Button) findViewById(R.id.btn_signup);
        tvForgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

}
