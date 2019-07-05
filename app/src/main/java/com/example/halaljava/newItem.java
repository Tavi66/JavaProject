package com.example.halaljava;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.halaljava.database.Expense;
import com.example.halaljava.database.Finance;

public class newItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        final Finance finance = new Finance(this);
        final EditText itemNameText = findViewById(R.id.itemTextField);
        final EditText itemAmount = findViewById(R.id.item_amount);
        final Spinner categoryDropDown = findViewById(R.id.categoryAll);
        ArrayAdapter<CharSequence> categoryAllAdapter = ArrayAdapter.createFromResource(this,R.array.category_expense_array,android.R.layout.simple_spinner_dropdown_item);
        categoryDropDown.setAdapter(categoryAllAdapter);

        findViewById(R.id.reset_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                counter = 0;
//                Snackbar.make(v, "Counter is reset!", Snackbar.LENGTH_LONG).show();
//                String s = "The count is: " + counter;
//                setText(s);
                finance.deleteDBTest();

            }
        });

        findViewById(R.id.database_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemNameText.getText() != null)
                {
                    finance.addExpense(finance.getExpense(new Expense()) );
                    Snackbar.make(v, "New row inserted!", Snackbar.LENGTH_LONG).show();
                    itemAmount.setText(null);
                    itemNameText.setText(null);
                    finance.setItemTitle(itemNameText.getText().toString());
                    finance.setItemAmount(0);

                }
                else
                    Snackbar.make(v, "Missing data!", Snackbar.LENGTH_LONG).show();

            }
        });

        itemNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0)
                    finance.setItemTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        itemAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0)
                    finance.setItemAmount(Double.parseDouble(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        findViewById(R.id.positive_amount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                byte b = 1;
                finance.setItemType(b);
            }
        });

        findViewById(R.id.negative_amount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                byte b = 0;
                finance.setItemType(b);
            }
        });

        categoryDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                finance.setCategory(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
