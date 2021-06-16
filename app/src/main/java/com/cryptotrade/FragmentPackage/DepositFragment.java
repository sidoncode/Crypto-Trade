package com.cryptotrade.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cryptotrade.AdapterPackage.DepositHistoryAdapter;
import com.cryptotrade.R;
import com.cryptotrade.AdapterPackage.ViewpagerImageAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;



public class DepositFragment extends Fragment {
    /**
     * field instance of all views
     */
    View rootView;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    RecyclerView recyclerViewDepositHistory;
    Timer timer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * assigning the layout of deposit and type casting of all child views
         */
        rootView = inflater.inflate(R.layout.layout_deposit, container, false);
initViews();

/**
 * setting up demo data
 */
        ArrayList<Integer> arrayList1 = new ArrayList<Integer>();
        arrayList1.add(R.drawable.visa1);
        arrayList1.add(R.drawable.mastercard1);
        arrayList1.add(R.drawable.paypal1);
        /**
         * setting demo  data to the view pager and adapter
         */
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("0.00005" + i);
        }
        recyclerViewDepositHistory.setAdapter(new DepositHistoryAdapter(arrayList, getActivity()));
        viewPager.setAdapter(new ViewpagerImageAdapter(getActivity(), arrayList1));
        circleIndicator.setViewPager(viewPager);

        timer = new Timer();
/**
 * scheduling timer for swiping image automatically
 */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() != null && isAdded()) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (viewPager.getAdapter() != null) {
                                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1) {
                                    viewPager.setCurrentItem(0, true);
                                } else {
                                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                                }
                            }
                        }
                    });
                }

            }
        }, 0, 5000);


        return rootView;
    }


    @Override
    public void onDestroy() {
        /**
         * canceling the timer on finishing
         */
        timer.cancel();
        super.onDestroy();
    }

    /***
     * initializing views
     */
    private void initViews(){
        /**
         * type casting all views and setting layout manager to the recycler view
         */
        recyclerViewDepositHistory = (RecyclerView) rootView.findViewById(R.id.recycler_deposit_history);

        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager_images);
        circleIndicator = (CircleIndicator) rootView.findViewById(R.id.indicator);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDepositHistory.setLayoutManager(linearLayoutManager);
    }

}
