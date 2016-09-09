package com.example.ankur.currencyconverter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText amountEd, currencyEd;
    TextView valueTv, inrTv;
    Button submitBtn;

    public static final String ACCESS_KEY = "63e94b6c7f1c181d141f6bab640a0295";
    public static final String BASE_URL = "http://apilayer.net/api/";
    public static final String ENDPOINT = "live";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountEd = (EditText) findViewById(R.id.amountEd);
        currencyEd = (EditText) findViewById(R.id.currencyEd);
        valueTv = (TextView) findViewById(R.id.valueTv);
        inrTv = (TextView) findViewById(R.id.inrTv);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

    }

    public void onClickAdd(View view) {
        Intent intent = new Intent(getApplicationContext(), AddNewRecord.class);
        startActivity(intent);
    }

    public void onClickBuyHistory(View view) {
        Intent intent = new Intent(getApplication(), BuyHistory.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        sendRequest();
    }

    private void sendRequest() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        showJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON(currencyEd.getText().toString());

        Double d = pj.d;
        Double inr = pj.inr;

        valueTv.setText(String.valueOf(inr/d));
        Double dd=Double.parseDouble(amountEd.getText().toString());
        inrTv.setText(String.valueOf(dd*(inr/d)));


    }
}
