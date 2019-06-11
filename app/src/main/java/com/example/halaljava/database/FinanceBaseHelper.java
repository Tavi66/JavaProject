package com.example.halaljava.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import com.example.halaljava.database.FinanceDbSchema.MonthlyExpensesTable;

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
        db.execSQL("Create table " + MonthlyExpensesTable.NAME + "(" +
                // MonthlyExpensesTable.Cols.ID + ", " +
                MonthlyExpensesTable.Cols.ID + " integer primary key autoincrement, " +
                MonthlyExpensesTable.Cols.CATEGORY + ", " +
                MonthlyExpensesTable.Cols.ITEM + ", " +
                MonthlyExpensesTable.Cols.DATE + ", " +
                MonthlyExpensesTable.Cols.AMOUNT + ", " +
                MonthlyExpensesTable.Cols.TYPE + ")"
        );
    }
    private void createMonthlyExpensesTable(){

    }
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

