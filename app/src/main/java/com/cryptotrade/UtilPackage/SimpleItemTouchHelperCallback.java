package com.cryptotrade.UtilPackage;
/**
 * all required libraries imported goes here
 */

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.cryptotrade.AdapterPackage.ItemTouchHelperAdapter;


public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    /**
     * field instance of all variable
     */
    private final ItemTouchHelperAdapter mAdapter;

    /**
     * constructor for getting item touch helper adapter
     * @param adapter
     */
    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    /**
     * enabling long press to re order
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
/*
enabling item swap
 */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * get movemnet flag and move the row as command
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * on move called moving of adapter row
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * on swipe called swipping adapter row
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

}