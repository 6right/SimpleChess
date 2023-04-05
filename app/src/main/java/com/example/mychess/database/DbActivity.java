package com.example.mychess.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbActivity {
    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbActivity(Context context){
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    public void openDb(){
        db = dbHelper.getWritableDatabase();
    }

    public void insertDb(String title){
        ContentValues values = new ContentValues();
        values.put(Constants.NICKNAME, title);
        db.insert(Constants.TABLE_NAME, null, values);
    }

}
