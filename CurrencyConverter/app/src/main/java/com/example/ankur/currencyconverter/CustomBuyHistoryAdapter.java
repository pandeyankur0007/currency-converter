package com.example.ankur.currencyconverter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ankur on 9/9/16.
 */
public class CustomBuyHistoryAdapter extends RecyclerView.Adapter<CustomBuyHistoryAdapter.ViewHolder> {

    private ArrayList<RecordDetail> listArray;
    Context c;
    private RecordDetail recordDetail;
    private DatabaseHelper dbHelper;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView idTv;
        public TextView dateTv;
        public TextView currencyTv;
        public TextView quantityTv;
        public TextView buyValueTv;
        public Button deleteBtn;

        public ViewHolder(View v) {
            super(v);
            idTv = (TextView) v.findViewById(R.id.idTv);
            dateTv = (TextView) v.findViewById(R.id.dateTv);
            currencyTv = (TextView) v.findViewById(R.id.currencyTv);
            quantityTv = (TextView) v.findViewById(R.id.quantityTv);
            buyValueTv = (TextView) v.findViewById(R.id.buyvalueTv);
            deleteBtn = (Button) v.findViewById(R.id.deleteBtn);
        }
    }

    /*  public void remove(String item) {
          int position = listArray.indexOf(item);
          listArray.remove(position);
          notifyItemRemoved(position);
      }
  */
    public CustomBuyHistoryAdapter(Context ctx, ArrayList<RecordDetail> listArray) {
        this.c = ctx;
        this.listArray = listArray;
    }

    @Override
    public CustomBuyHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_buy_history, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomBuyHistoryAdapter.ViewHolder holder, final int position) {
        recordDetail = listArray.get(position);
        dbHelper = DatabaseHelper.getInstance(c);
        holder.idTv.setText(recordDetail.getId() + "");
        holder.dateTv.setText(recordDetail.getDate());
        holder.currencyTv.setText(recordDetail.getCurrency());
        holder.quantityTv.setText(recordDetail.getQuantity() + "");
        holder.buyValueTv.setText(recordDetail.getBuyValue() + "");

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dbHelper.delete(listArray.get(position));
                if (result == true) {
                    listArray.remove(position);
                    notifyItemRemoved(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listArray.size();
    }
}