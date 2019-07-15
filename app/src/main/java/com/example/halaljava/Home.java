package com.example.halaljava;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.halaljava.database.Expense;
import com.example.halaljava.database.Finance;
import com.example.halaljava.database.FinanceAdapter;

import java.util.ArrayList;

public class Home extends Fragment {

    RecyclerView recycler;
    FinanceAdapter financeAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        recycler = getView().findViewById( R.id.monthlyExpensesView);
        updateUI();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator( new DefaultItemAnimator());

        recycler.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        FloatingActionButton fab = getView().findViewById(R.id.add_new_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),newItem.class));
            }
        });
    }

    private void updateUI(){
        Finance finance = new Finance(getContext());
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
