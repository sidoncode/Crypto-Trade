package com.cryptocurrencybestrate.ethereum.AdapterPackage;
/*
all required libraries imported here
 */

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class ViewpagerImageAdapter extends PagerAdapter {
    /**
     * field instances of all views and variables
     */
    private Activity context;
    ArrayList<Integer> arrayList;
    int pre = 0;

    /**
     * constructor for getting context and list
     *
     * @param context
     * @param arrayList
     */
    public ViewpagerImageAdapter(Activity context, ArrayList<Integer> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    /**
     * return total item count
     *
     * @return
     */
    @Override
    public int getCount() {
        return arrayList.size();
    }

    /**
     * assigning view to the row
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    /**
     * initiating each item with image
     *
     * @param container
     * @param position
     * @return
     */

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams layoutParamsImage = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        imageView.setLayoutParams(layoutParamsImage);

        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        /**
         * setting image to the image view according to the position
         */
        Glide.with(context).load(arrayList.get(position)).into(imageView);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    /*
    destroying each item after scroll
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
