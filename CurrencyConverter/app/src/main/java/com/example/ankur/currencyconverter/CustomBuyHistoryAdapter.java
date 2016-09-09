package com.example.ankur.currencyconverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by ankur on 9/9/16.
 */
public class CustomBuyHistoryAdapter extends ArrayAdapter<RecordDetail> {

    final ArrayList<RecordDetail> listArray = new ArrayList<>();

    public CustomBuyHistoryAdapter(Context context, ArrayList<RecordDetail>listArray) {
        super(context, 0, listArray);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       final RecordDetail listRecord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_buy_history, parent, false);
        }

        TextView id = (TextView) convertView.findViewById(R.id.idTv);
        TextView date = (TextView) convertView.findViewById(R.id.dateTv);
        TextView currency = (TextView) convertView.findViewById(R.id.currencyTv);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantityTv);
        TextView buyValue = (TextView) convertView.findViewById(R.id.buyvalueTv);
        Button deleteBtn = (Button) convertView.findViewById(R.id.deleteBtn);

        id.setText(listRecord.id+"");
        date.setText(listRecord.date);
        currency.setText(listRecord.currency);
        quantity.setText(listRecord.quantity+"");
        buyValue.setText(listRecord.getBuyValue()+"");

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = null;
                boolean result = dbHelper.delete(listArray.get(position));
                if(result){
                    listArray.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }
}
