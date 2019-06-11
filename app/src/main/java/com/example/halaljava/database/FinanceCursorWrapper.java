package com.example.halaljava.database;

import android.database.Cursor;
import android.database.CursorWrapper;

public class FinanceCursorWrapper extends CursorWrapper {
    public FinanceCursorWrapper (Cursor cursor){
        super(cursor);
    }

    public Finance getMonthlyExpense(){
        int id = getInt(getColumnIndex(FinanceDbSchema.MonthlyExpensesTable.Cols.ID));
        String category = getString(getColumnIndex(FinanceDbSchema.MonthlyExpensesTable.Cols.CATEGORY));
        String name = getString(getColumnIndex(FinanceDbSchema.MonthlyExpensesTable.Cols.ITEM));
        String date = getString(getColumnIndex(FinanceDbSchema.MonthlyExpensesTable.Cols.DATE));
        double amount = getDouble(getColumnIndex(FinanceDbSchema.MonthlyExpensesTable.Cols.AMOUNT));
        int type = getInt(getColumnIndex(FinanceDbSchema.MonthlyExpensesTable.Cols.TYPE));

        Finance finance = new Finance();

        return finance;
    }
}
