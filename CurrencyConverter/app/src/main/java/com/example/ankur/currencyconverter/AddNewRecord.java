package com.example.ankur.currencyconverter;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewRecord extends AppCompatActivity implements View.OnClickListener {
    EditText dateEd, currencyEd, quantityEd, buyValueEd;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

        dateEd = (EditText) findViewById(R.id.dateEd);
        currencyEd = (EditText) findViewById(R.id.currencyEd);
        quantityEd = (EditText) findViewById(R.id.quantityEd);
        buyValueEd = (EditText) findViewById(R.id.buyvalueEd);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        dateEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                DatePickerDialog dialog = new DatePickerDialog(AddNewRecord.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String str = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
                        dateEd.setText(str);
                    }
                },2016,9,9);
                dialog.show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        String date = dateEd.getText().toString();
        String currency = currencyEd.getText().toString();
        int quantity = Integer.parseInt(quantityEd.getText().toString());
        double buyValue = Double.parseDouble(buyValueEd.getText().toString());

        DatabaseHelper db = new DatabaseHelper(AddNewRecord.this);
        boolean result = db.saveRecord(new RecordDetail(date, currency,0,quantity, buyValue));
        Log.e("insert result : ", result+"");
        db.close();
        
        if (result == true) {
            dateEd.setText("");
            currencyEd.setText("");
            quantityEd.setText("");
            buyValueEd.setText("");

            Toast.makeText(AddNewRecord.this, "Record is Added", Toast.LENGTH_SHORT).show();
        }
    }
}
