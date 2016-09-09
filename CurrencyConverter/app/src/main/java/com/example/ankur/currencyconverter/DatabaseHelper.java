package com.example.ankur.currencyconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ankur on 9/9/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String tablename = "recordInfo";  // tablename
    private static final String date = "date";  // column name
    private static final String id = "ID";  // auto generated ID column
    private static final String currency = "currency"; // column name
    private static final String quantity = "quantity"; // column name
    private static final String buyvalue = "buyvalue"; // column name
    private static final String databasename = "recordDb"; // Dtabasename
    private static final int versioncode = 1; //versioncode of the database

    public DatabaseHelper(Context context) {
        super(context, databasename, null, versioncode);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query = "CREATE TABLE IF NOT EXISTS " + tablename + "(" + id + " integer primary key, " + date + " text, "+ currency + " text,"+ quantity + " integer," + buyvalue + " real)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query;
        query = "DROP TABLE IF EXISTS " + tablename;
        db.execSQL(query);
        onCreate(db);
    }

    public boolean saveRecord(RecordDetail recordDetail){
        // store column name and corresponding values

        ContentValues cv = new ContentValues();
        cv.put(date, recordDetail.getDate());
        cv.put(currency, recordDetail.getCurrency());
        cv.put(quantity, recordDetail.getQuantity());
        cv.put(buyvalue, recordDetail.getBuyValue());

        // execute insert query with values from CV
        SQLiteDatabase db = getWritableDatabase();
        long r = db.insert(tablename, null, cv);
        db.close();

        if(r != -1)
            return true;
        else return false;

    }

    public ArrayList<RecordDetail> getRecord(){
        ArrayList<RecordDetail> recordArrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+tablename, null);
        while (cursor.moveToNext() == true){
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String currency = cursor.getString(2);
            int quantity = cursor.getInt(3);
            double buyValue = cursor.getDouble(4);

            recordArrayList.add(new RecordDetail(date, currency,id,quantity, buyValue));
        }
        cursor.close();
        db.close();

        return recordArrayList;
    }

    public boolean delete(RecordDetail recordId) {

        SQLiteDatabase db = getWritableDatabase();
        Log.e("OutCome", recordId+"");
        long r = db.delete(tablename, id+" =?", new String[]{recordId+""});
        db.close();
        if(r != 1) return true;
        else return false;
    }

}
