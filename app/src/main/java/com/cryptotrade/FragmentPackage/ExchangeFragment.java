package com.cryptotrade.FragmentPackage;
/***
 * all required libraries imported here
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cryptotrade.ActivityPackage.HomeActivity;
import com.cryptotrade.R;


public class ExchangeFragment extends Fragment {
    /**
     * field instances of all views and variables
     */
    View rootView;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * setting exchange layout to this screen
         */
        rootView = inflater.inflate(R.layout.layout_exchange, container, false);

        initViews();
        /**
         * opening trade screen by default
         */
        //((HomeActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.replace_fragment_on_tab_clicks, new TradeFragment()).commitAllowingStateLoss();

        /**
         * tab selected listener
         */
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    /**
                     * opening trade screen
                     */
                    //((HomeActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.replace_fragment_on_tab_clicks, new TradeFragment()).commitAllowingStateLoss();
                } else if (tab.getPosition() == 1) {
                    /**
                     * opening margin screen
                     */
                    ((HomeActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.replace_fragment_on_tab_clicks, new MarginFragment()).commitAllowingStateLoss();
                } else {
                    /**
                     * opening deposit screen
                     */
                    ((HomeActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.replace_fragment_on_tab_clicks, new DepositFragment()).commitAllowingStateLoss();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }

    private void initViews() {
        /**
         * type casting views
         */
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
/**
 * adding three tabs
 */
        tabLayout.addTab(tabLayout.newTab().setText("Trade"));
        tabLayout.addTab(tabLayout.newTab().setText("Margin"));
        tabLayout.addTab(tabLayout.newTab().setText("Deposit"));

    }
}
