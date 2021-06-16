package com.cryptotrade.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.cryptotrade.ActivityPackage.LoginActivity;
import com.cryptotrade.R;


public class SignupFragment extends Fragment {
    /**
     * field instance of all views
     */
    View rootView;
    Button btnSignIn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * assigning the layout of sign up and type casting of all child views
         */
        rootView = inflater.inflate(R.layout.layout_signup, container, false);

        btnSignIn = (Button) rootView.findViewById(R.id.btn_login);
        /**
         * click listener of sign in text view
         */
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * popping the sign up fragment to show the sign in screen
                 */
                LoginActivity.manager.popBackStackImmediate();
            }
        });
        return rootView;
    }
}
