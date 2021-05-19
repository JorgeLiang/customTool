package com.jorge.customtool.ToolTest.recyclerViewHeaderFooder;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jorge.customtool.MainActivity;
import com.jorge.customtool.R;
import com.jorge.customtool.ToolTest.BaseActivity;
import com.jorge.multiple_recyclerview.MultipleRecyclerviewAdapter;

import java.util.Random;

/**
 * Created by Jorge on 5/19/21.
 */

public class MultipleRecyclerActiviy extends BaseActivity {

    private Random random = new Random(100);
    private RecyclerView.Adapter adapter;
    private CardView headerView, footerView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_recycler);

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new RecyclerView.Adapter<ViewHolder>() {

            boolean isStaggered;

            @Override
            public void onAttachedToRecyclerView(RecyclerView recyclerView) {
                super.onAttachedToRecyclerView(recyclerView);
                isStaggered = recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager;
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                CardView cardView = new CardView(MultipleRecyclerActiviy.this);
                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 100);
                layoutParams.setMargins(10, 10, 10, 10);
                cardView.setLayoutParams(layoutParams);

                TextView textView = new TextView(MultipleRecyclerActiviy.this);
                textView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.BLACK);

                cardView.addView(textView);

                ViewHolder viewHolder = new ViewHolder(cardView);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                int[] heights = {300, 400, 500};
                FrameLayout frameLayout = (FrameLayout) holder.itemView;
                if (isStaggered) {
                    ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
                    int height = heights[random.nextInt(3)];
                    lp.height = height;
                    holder.itemView.setLayoutParams(lp);
                    holder.itemView.setBackgroundColor(Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                }
                ((TextView) frameLayout.getChildAt(0)).setText("positon: " + position);
            }

            @Override
            public int getItemCount() {
                return 50;
            }
        };

        initHeadAndFooterView();
        showLine();
    }

    private void initHeadAndFooterView() {
        //header view
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 400);
        layoutParams.setMargins(10, 10, 10, 10);

        headerView = new CardView(this);
        headerView.setLayoutParams(layoutParams);
        TextView head = new TextView(this);
        head.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        head.setBackgroundColor(Color.BLUE);
        head.setTextColor(Color.WHITE);
        head.setGravity(Gravity.CENTER);
        head.setText("THIS IS HEADER VIEW");
        headerView.addView(head);

        //footer view
        footerView = new CardView(this);
        footerView.setLayoutParams(layoutParams);
        TextView footer = new TextView(this);
        footer.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        footer.setBackgroundColor(Color.RED);
        footer.setText("THIS IS FOOTER VIEW");
        footer.setGravity(Gravity.CENTER);
        footer.setTextColor(Color.WHITE);
        footerView.addView(footer);
    }

    public void btn_showLine(View view) {
        showLine();
    }

    public void action_grid(View view) {
        showGrid();
    }

    public void action_staggered(View view) {
        showStagger();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private void showLine() {
        Toast.makeText(this, "LinerLayoutManager", Toast.LENGTH_SHORT).show();
        setTitle("LinearLayoutManager");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        MultipleRecyclerviewAdapter multipleRecyclerviewAdapter = new MultipleRecyclerviewAdapter(adapter);
        multipleRecyclerviewAdapter.setFooterView(footerView);
        multipleRecyclerviewAdapter.setHeaderView(headerView);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(multipleRecyclerviewAdapter);
    }

    private void showGrid() {
        Toast.makeText(this, "GridLayoutManager", Toast.LENGTH_SHORT).show();
        setTitle("GridLayoutManager");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        MultipleRecyclerviewAdapter multipleRecyclerviewAdapter = new MultipleRecyclerviewAdapter(adapter);
        multipleRecyclerviewAdapter.setFooterView(footerView);
        multipleRecyclerviewAdapter.setHeaderView(headerView);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(multipleRecyclerviewAdapter);
    }

    private void showStagger() {
        Toast.makeText(this, "StaggeredGridLayoutManager", Toast.LENGTH_SHORT).show();
        setTitle("StaggeredGridLayoutManager");
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        MultipleRecyclerviewAdapter multipleRecyclerviewAdapter = new MultipleRecyclerviewAdapter(adapter);
        multipleRecyclerviewAdapter.setFooterView(footerView);
        multipleRecyclerviewAdapter.setHeaderView(headerView);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(multipleRecyclerviewAdapter);
    }
}
