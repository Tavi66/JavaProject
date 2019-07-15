package com.example.halaljava.database;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.halaljava.ItemTouchHelperAdapter;
import com.example.halaljava.R;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class FinanceAdapter extends RecyclerView.Adapter<FinanceAdapter.FinViewHolder> implements ItemTouchHelperAdapter {

    private ArrayList<Expense> expenses;
    private int itemLayout;
    //private Finance finance;
    public FinanceAdapter(ArrayList<Expense> e, int itemLayout){
        this.expenses = e;
        this.itemLayout = itemLayout;
    }

    @NonNull
    @Override
    public FinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_row,parent,false);
        return new FinViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FinViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.itemTitle.setText(expense.getItemTitle());
        holder.itemAmount.setText(expense.getItemAmount());
        holder.category.setText(expense.getCategory());
        holder.date.setText("Testing");//expense.getDate()
        holder.itemType.setText(expense.getItemType());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemDismiss(int position) {

    }

    public void removeItem(int position) {

    }

    public static class FinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView itemTitle, category,itemAmount,itemType,date;

        public FinViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemNameDisplay);
            category = itemView.findViewById(R.id.categoryDisplay);
            itemAmount = itemView.findViewById(R.id.amountDisplay);
            itemType = itemView.findViewById(R.id.typeDisplay);
            date = itemView.findViewById(R.id.dateDisplay);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick " + getAdapterPosition() + getItemId());
        }

    };


    public FinanceAdapter (ArrayList<Expense> financeArrayList){
        expenses = financeArrayList;
    }
}
