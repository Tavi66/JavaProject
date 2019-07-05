package com.example.halaljava;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.view.View;
import com.example.halaljava.database.Expense;
import com.example.halaljava.database.Finance;
import com.example.halaljava.database.FinanceAdapter;

import java.util.ArrayList;

public class Home extends FragmentActivity {

    RecyclerView recycler;
    FinanceAdapter financeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        recycler = findViewById( R.id.monthlyExpensesView);
        updateUI();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Home.this);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator( new DefaultItemAnimator());

        recycler.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,newItem.class));
            }
        });
    }

    private void updateUI(){
        Finance finance = new Finance(Home.this);
        ArrayList<Expense> expenses = finance.getMonthlyExpenses();

        financeAdapter = new FinanceAdapter(expenses);
        recycler.setAdapter(financeAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
