package com.jorge.customtool.ToolTest.blurr;

import android.content.Context;
import android.content.res.TypedArray;

import androidx.annotation.NonNull;

import com.jorge.customtool.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 6/28/21.
 */

public class ListItemDataProvider {

    public static List<ListItemData> generateSample(@NonNull Context context, int repeatCount) {
        List<ListItemData> list = new ArrayList<>();
        TypedArray images = context.getResources().obtainTypedArray(R.array.list_item_images);
        String[] titles = context.getResources().getStringArray(R.array.list_item_titles);
        String[] descriptions = context.getResources().getStringArray(R.array.list_item_descriptions);

        int imagesCount = images.length();
        if (imagesCount== titles.length && imagesCount == descriptions.length) {
            // add 5 times to make the list longer
            for (int repeat = 0; repeat < repeatCount; repeat++) {
                for (int i = 0; i < imagesCount; i++) {
                    list.add(new ListItemData(images.getResourceId(i, 0), titles[i], descriptions[i]));
                }
            }
        }

        images.recycle();

        return list;
    }
}
