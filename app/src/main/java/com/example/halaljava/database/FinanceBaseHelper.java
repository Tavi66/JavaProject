package com.example.halaljava.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import com.example.halaljava.database.FinanceDbSchema.MonthlyExpensesTable;

import java.util.ArrayList;

public class FinanceBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "FinanceBase.db";
    private static FinanceBaseHelper Instance;

    public static synchronized  FinanceBaseHelper getInstance(Context context) {
        if(Instance == null) {
            Instance = new FinanceBaseHelper(context.getApplicationContext());
        }
        return Instance;
    }

    public FinanceBaseHelper(Context context) {
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Test: Creating monthly expenses table
        db.execSQL(MonthlyExpensesTable.SQL_CREATE);
        db.execSQL(FinanceDbSchema.walletTable.SQL_CREATE);
    }

    public String getDatabaseName(){
        return DATABASE_NAME;
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

