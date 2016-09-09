package com.example.ankur.currencyconverter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ankur on 9/9/16.
 */
public class ParseJSON {

    public static Double inr;
    public static Double d;

    public static final String QUOTES = "quotes";
    public static final String SOURCE = "USD";
    public static final String SUCCESS = "success";

    private String json;

    public ParseJSON(String json) {
        this.json = json;
    }

    protected void parseJSON(String currency) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(json);
            String responseCode = jsonObject.getString(SUCCESS);
            if (responseCode == "true") {
                JSONObject quotesObject = jsonObject.getJSONObject(QUOTES);
                d = quotesObject.getDouble(SOURCE + currency);
                inr = quotesObject.getDouble("USDINR");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
