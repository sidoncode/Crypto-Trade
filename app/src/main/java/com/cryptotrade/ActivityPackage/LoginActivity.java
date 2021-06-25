package com.cryptocurrencybestrate.ethereum.ActivityPackage;
/**
 * all required libraries imported here
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.cryptocurrencybestrate.ethereum.FragmentPackage.MarketFragment;
import com.cryptocurrencybestrate.ethereum.FragmentPackage.SignupFragment;
import com.cryptocurrencybestrate.ethereum.R;
import com.cryptocurrencybestrate.ethereum.UtilPackage.Utility;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginActivity extends AppCompatActivity {
    /**
     * Field instances of views and variables
     */
    Button btnSignup;
    public static FragmentManager manager;
    TextView tvForgotPassword;
    Button btnLogin;
    private  TextView g_signin;
    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 234;
    private static String  TAG  = "SIDD";



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



        mAuth = FirebaseAuth.getInstance();

        FirebaseApp.getInstance();
        FirebaseApp.initializeApp(LoginActivity.this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // init google signin //
        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);
        // signIn();//





        /**
         * assigning the support fragment manager for future fragment replacement from any where
         */
        manager = getSupportFragmentManager();

        if(!Utility.getUserFirstName(LoginActivity.this).equals("")){
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        if(!Utility.g_getUserFirstName(LoginActivity.this).equals("")){
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        /**
         * Type casting of all views of login screen
         */
        initialiseViews();

/**
 * click listener of signup text which will redirect to the sign up screen
 */



        g_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


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


    private void signIn() {
        //getting the google signin intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        //starting the activity for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //authenticating with firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        //getting the auth credential
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        //Now using firebase we are signing in the user here
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(LoginActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();

                            //startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                            Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                            i.putExtra("user_name",user.getDisplayName());
                            i.putExtra("user_pic",user.getPhotoUrl());
                            Utility.g_setUserFirstName(LoginActivity.this,user.getDisplayName());
                            startActivity(i);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

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
        g_signin = findViewById(R.id.g_signin);
    }

}
