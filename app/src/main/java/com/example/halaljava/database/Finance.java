package com.example.halaljava.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.*;

public class Finance {
    private Finance sFinance;

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static String itemTitle;
    private static String category;
    private  static double itemAmount;
    private static byte itemType;
    private FinanceAdapter financeAdapter;

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
//        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.ID, rand.nextInt(50));
        Date date = Calendar.getInstance().getTime();
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.CATEGORY, category);
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.ITEM, itemTitle);
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.DATE, date.toString());
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.AMOUNT, itemAmount);
        values.put(FinanceDbSchema.MonthlyExpensesTable.Cols.TYPE, itemType);
        return values;
    }

    public ArrayList<Expense> getMonthlyExpenses(){
        ArrayList <Expense> arrayList = new ArrayList<>();
        SQLiteDatabase db = new FinanceBaseHelper(mContext).getReadableDatabase();
        Cursor res = db.rawQuery(FinanceDbSchema.MonthlyExpensesTable.SQL_SELECT, null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            String n = res.getString(res.getColumnIndex(FinanceDbSchema.MonthlyExpensesTable.Cols.ITEM) );
            Double a = res.getDouble(res.getColumnIndex(FinanceDbSchema.MonthlyExpensesTable.Cols.AMOUNT));
            String c = res.getString(res.getColumnIndex(FinanceDbSchema.MonthlyExpensesTable.Cols.CATEGORY) );
            String temp = res.getString(res.getColumnIndex(FinanceDbSchema.MonthlyExpensesTable.Cols.TYPE) );
            Byte t = Byte.parseByte(temp);
            arrayList.add( new Expense(n,a,c,t));
            res.moveToNext();
        }
        res.close();
        return arrayList;
    }

    public void deleteDBTest() {
        FinanceBaseHelper fin = new FinanceBaseHelper(mContext);
        mDatabase.execSQL(FinanceDbSchema.MonthlyExpensesTable.SQL_DROP);
        mDatabase.execSQL(FinanceDbSchema.MonthlyExpensesTable.SQL_CREATE);
        mDatabase.execSQL(FinanceDbSchema.walletTable.SQL_DROP);
        mDatabase.execSQL(FinanceDbSchema.walletTable.SQL_CREATE);
    }

    public void createMonthlyExpensesTable(){
        mDatabase.execSQL("Create table " + FinanceDbSchema.MonthlyExpensesTable.NAME + "(" +
                // MonthlyExpensesTable.Cols.ID + ", " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.ID + " integer primary key autoincrement, " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.CATEGORY + ", " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.ITEM + ", " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.DATE + ", " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.AMOUNT + ", " +
                FinanceDbSchema.MonthlyExpensesTable.Cols.TYPE + ")"
        );
    }

    public void createWalletTable(){
        mDatabase.execSQL("CREATE TABLE " + FinanceDbSchema.walletTable.NAME + "(" +
                FinanceDbSchema.walletTable.Cols.WALLETTYPE + " primary key," +
                FinanceDbSchema.walletTable.Cols.AMOUNT + " not null)"
        );
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
    public double getItemAmount() { return itemAmount; }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}
