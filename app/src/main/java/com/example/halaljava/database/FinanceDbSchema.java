package com.example.halaljava.database;

public class FinanceDbSchema {
    public static final class MonthlyExpensesTable {
        public static final String NAME = "monthlyExpenses";

        public static final class Cols {
            public static final String ID = "itemId";
            public static final String CATEGORY = "category";
            public static final String ITEM = "item";
            public static final String DATE = "date";
            public static final String AMOUNT = "amount";
            public static final String TYPE = "type";
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
