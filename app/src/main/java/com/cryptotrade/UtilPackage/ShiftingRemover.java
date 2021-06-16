package com.cryptotrade.UtilPackage;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import java.lang.reflect.Field;



/**
 * this class if for removing shift animation of bottom navigation view
 */
public class ShiftingRemover {

   public static void removeShiftMode(BottomNavigationView view) {
       /**
        * getting menu view of bottom nav
        */
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            /**
             * getting field
             */
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            /**
             * setting shifting accessible enabled true and other
             */
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            /*
            looping through the all item of navigation views and setting each items shifting animation false
             */
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }
}