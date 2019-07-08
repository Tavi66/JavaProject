package com.example.halaljava;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.halaljava.database.Expense;
import com.example.halaljava.database.Finance;
import com.example.halaljava.database.FinanceAdapter;

import java.util.ArrayList;

public class HomeFrag extends Fragment {

    RecyclerView recycler;
    FinanceAdapter financeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = getView().findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        recycler = getView().findViewById( R.id.monthlyExpensesView);
        updateUI();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator( new DefaultItemAnimator());

        recycler.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        FloatingActionButton fab = getView().findViewById(R.id.fab);
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
