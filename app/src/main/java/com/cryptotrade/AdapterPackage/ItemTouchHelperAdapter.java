package com.cryptotrade.AdapterPackage;


/**
 * interface for re ordering coin list row
 */
public interface ItemTouchHelperAdapter {

    /**
     * will fire on item move to another place
     * @param fromPosition
     * @param toPosition
     */

    void onItemMove(int fromPosition, int toPosition);

    /**
     * will fire on item dismiss
     * @param position
     */
    void onItemDismiss(int position);
}