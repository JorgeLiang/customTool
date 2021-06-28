package com.jorge.squareblur;

import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;

/**
 * Created by Jorge on 6/28/21.
 */

class BlurDrawerListener extends DrawerLayout.SimpleDrawerListener {

    private final BlurringView blurringView;

    public BlurDrawerListener(BlurringView blurringView) {
        this.blurringView = blurringView;
        this.blurringView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        if (blurringView.getVisibility() != View.VISIBLE) {
            blurringView.setVisibility(View.VISIBLE);
        }
        blurringView.setAlpha(slideOffset);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        blurringView.setVisibility(View.INVISIBLE);
    }
}
