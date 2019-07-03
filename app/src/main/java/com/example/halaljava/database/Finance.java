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
    private Finance sFinance;

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static String itemTitle;
    private static String category;
    private  static double itemAmount;
    private static byte itemType;

    public Finance() {}

    public Finance(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new FinanceBaseHelper(mContext).getWritableDatabase();
    }

    public byte getItemType() {
        return itemType;
    }

    public void setItemType(byte itemType) {
        Finance.itemType = itemType;
    }

    public void setItemAmount(double itemAmount) {
        Finance.itemAmount = itemAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String c) {
        category = c;
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
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.CATEGORY, category);
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.ITEM, itemTitle);
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.DATE, Calendar.getInstance().getTime().toString());
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.AMOUNT, itemAmount);
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.TYPE, itemType);
        return values;
    }

    public void deleteDBTest() {
        FinanceBaseHelper fin = new FinanceBaseHelper(mContext);
        String s = "DROP TABLE " + FinanceDbSchema.MonthlyExpensesTable.NAME;
        mDatabase.execSQL(s);
         s = "CREATE TABLE " + FinanceDbSchema.MonthlyExpensesTable.NAME + "(" +
                // MonthlyExpensesTable.Cols.ID + ", " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.ID + " integer primary key autoincrement, " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.CATEGORY + ", " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.ITEM + ", " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.DATE + ", " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.AMOUNT + ", " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.TYPE + ")";
        mDatabase.execSQL(s);
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

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}
