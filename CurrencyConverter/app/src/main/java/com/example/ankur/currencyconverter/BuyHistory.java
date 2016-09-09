package com.example.ankur.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BuyHistory extends AppCompatActivity {

    TextView recordInfoTv;
    ListView listView;
    ArrayList<RecordDetail> listArray = new ArrayList<>();
    CustomBuyHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_history);
        recordInfoTv = (TextView) findViewById(R.id.recordInfoTv);
        listView = (ListView) findViewById(R.id.recordListView);

        try {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            listArray = dbHelper.getRecord();
            adapter = new CustomBuyHistoryAdapter(this, listArray);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
           // String length = String.valueOf(listArray.size());
           // recordInfoTv.setText(length + " record(s)");

        } catch (Exception ex) {
            recordInfoTv.setText(ex.getMessage().toString());
        }

    }
}
