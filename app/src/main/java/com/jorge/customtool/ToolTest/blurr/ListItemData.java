package com.jorge.customtool.ToolTest.blurr;

import androidx.annotation.DrawableRes;

/**
 * Created by Jorge on 6/28/21.
 */

public class ListItemData {
    @DrawableRes
    public final int imageRes;
    public final String title;
    public final String description;

    public ListItemData(@DrawableRes int imageRes, String title, String description) {
        this.imageRes = imageRes;
        this.title = title;
        this.description = description;
    }
}
