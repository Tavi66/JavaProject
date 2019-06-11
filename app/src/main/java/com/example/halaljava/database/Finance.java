package com.example.halaljava.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Finance {
    private static Finance sFinance;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public Finance() {}

    public Finance(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new FinanceBaseHelper(mContext).getWritableDatabase();
    }

    public void addExpense(Expense e) {
        ContentValues values = getContentValues(e);
        mDatabase.insert(FinanceDbSchema.MonthlyExpensesTable.NAME,null,values);
    }

    public List<Expense> getFinances() {
        return new ArrayList<>();
    }

    public Expense getExpense(Expense id) {
        return null;
    }
    //Expense expense [create functions if importing data]
    //Need to create functions to get user input then add values
    public static ContentValues getContentValues (Expense expense) {
        ContentValues values = new ContentValues();
        Random rand = new java.util.Random();

//        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.ID, rand.nextInt(50));
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.CATEGORY, "Food");
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.DATE, Calendar.getInstance().getTime().toString());
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.ITEM, "McDonald's");
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.AMOUNT, (double) rand.nextInt(250));
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.TYPE, 0);
        return values;
    }

    private FinanceCursorWrapper queryFinance(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                FinanceDbSchema.MonthlyExpensesTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new FinanceCursorWrapper(cursor);
    }

}
