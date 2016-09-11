package com.example.ankur.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BuyHistory extends AppCompatActivity {
    private RecyclerView recordRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<RecordDetail> listArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_history);
        recordRecyclerView = (RecyclerView) findViewById(R.id.record_recycler_view);

        recordRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recordRecyclerView.setLayoutManager(mLayoutManager);

        try {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            listArray = dbHelper.getRecord();
            mAdapter = new CustomBuyHistoryAdapter(this, listArray);
            recordRecyclerView.setAdapter(mAdapter);


        } catch (Exception ex) {
            Toast.makeText(BuyHistory.this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }
}
