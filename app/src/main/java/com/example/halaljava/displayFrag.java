package com.example.halaljava;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.*;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.halaljava.database.Expense;
import com.example.halaljava.database.Finance;
import com.example.halaljava.database.FinanceAdapter;

import java.util.ArrayList;

public class displayFrag extends Fragment {

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
        initView();
       // Toolbar toolbar = getView().findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
       // final editItemFrag editItemFragment = new editItemFrag();
       // FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
       // fragmentTransaction.add(R.id.home_container, editItemFragment, editItemFragment.toString());
       // fragmentTransaction.addToBackStack(null);
       // fragmentTransaction.commit();

//        recycler = getView().findViewById( R.id.monthlyExpensesView);
//        updateUI();
//
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
//
//        recycler.setLayoutManager(mLayoutManager);
//        recycler.setItemAnimator( new DefaultItemAnimator());
//        financeAdapter.notifyDataSetChanged();
//
//        final SwipeController swipeController = new SwipeController();
//        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
//        itemTouchhelper.attachToRecyclerView(recycler);
//        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                swipeController.onDraw(c);
//                //super.onDraw(c, parent, state);
//            }
//        });
//        recycler.setAdapter(financeAdapter);
//
//        FloatingActionButton fab = getView().findViewById(R.id.add_new_item);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                newItemFrag newItemFragment = new newItemFrag();
//                //editItemFrag editItemFragment = new editItemFrag();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.home_container, newItemFragment, newItemFragment.toString());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//
//        });
//
//        swipe();

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(),newItem.class));
//            }
//        });
    }

    private void initView(){
        recycler = getView().findViewById( R.id.monthlyExpensesView);
        updateUI();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);

        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator( new DefaultItemAnimator());
        financeAdapter.notifyDataSetChanged();

        final SwipeController swipeController = new SwipeController();
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recycler);
        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                swipeController.onDraw(c);
                //super.onDraw(c, parent, state);
            }
        });
        recycler.setAdapter(financeAdapter);

        FloatingActionButton fab = getView().findViewById(R.id.add_new_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newItemFrag newItemFragment = new newItemFrag();
                //editItemFrag editItemFragment = new editItemFrag();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_container, newItemFragment, newItemFragment.toString());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });

        swipe();

    }

    private void swipe(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if(direction == ItemTouchHelper.LEFT)
                {
                    financeAdapter.removeItem(position);
                    Toast.makeText(getContext(),"Delete item.",Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(getContext(),"Edit item.",Toast.LENGTH_SHORT).show();

                }
            }
        };
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
