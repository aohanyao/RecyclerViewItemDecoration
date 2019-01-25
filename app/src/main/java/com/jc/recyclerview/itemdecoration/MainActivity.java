package com.jc.recyclerview.itemdecoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<RecyclerBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.mRecyclerView);
        initRecyclerView();

    }

    private void initRecyclerView() {
        mockData();

        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(mDatas, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new LineItemDecoration(this, mDatas));
//        mRecyclerView.addItemDecoration(new LinearLayoutColorDivider(getResources(), R.color.colorPrimary,
//                R.dimen.fab_margin));
    }

    private void mockData() {
        mDatas = new ArrayList<>();
        int temp = 0;
        for (int i = 0; i < 150; i++) {
            if (i % 4 == 0 || i % 7 == 0) {
                temp++;
            }
            mDatas.add(new RecyclerBean(String.valueOf(temp)));
        }
    }


}
