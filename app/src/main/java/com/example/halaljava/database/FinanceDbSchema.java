package com.example.halaljava.database;

public class FinanceDbSchema {
    public static final class MonthlyExpensesTable {
        public static final String NAME = "monthlyExpenses";
        public static final String SQL_CREATE = "CREATE TABLE " + NAME + "(" +
                MonthlyExpensesTable.Cols.ID + " integer primary key autoincrement, " +
                MonthlyExpensesTable.Cols.CATEGORY + ", " +
                MonthlyExpensesTable.Cols.ITEM + " not null , " +
                MonthlyExpensesTable.Cols.DATE + ", " +
                MonthlyExpensesTable.Cols.AMOUNT + " not null , " +
                MonthlyExpensesTable.Cols.TYPE + ")";
        public static final String SQL_DROP = "DROP TABLE " + NAME;
        public static final String SQL_SELECT = "SELECT * FROM " + NAME;

        public static final class Cols {
            public static final String ID = "itemId";
            public static final String CATEGORY = "category";
            public static final String ITEM = "item";
            public static final String DATE = "date";
            public static final String AMOUNT = "amount";
            public static final String TYPE = "type";
        }

    }

    public static final class walletTable {
        public static final String NAME = "wallet";
        public static final String SQL_CREATE = "CREATE TABLE " + NAME + "(" +
                walletTable.Cols.WALLETTYPE + " primary key," +
                walletTable.Cols.AMOUNT + ")";
        public static final String SQL_DROP = "DROP TABLE " + NAME;

        public static final class Cols {
            public static final String WALLETTYPE = "walletType";
            public static final String AMOUNT = "amount";
        }
    }

    public static final class Debt {
        public static final String NAME = "debt";

        public static final class Cols {
            public static final String ID = "itemId";
            public static final String CATEGORY = "category";
            public static final String ITEM = "item";
            public static final String DATE = "date";
            public static final String AMOUNT = "amount";
            public static final String TYPE = "type";
        }
    }


}
