package com.example.halaljava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import com.example.halaljava.database.Expense;
import com.example.halaljava.database.FinanceBaseHelper;
import com.example.halaljava.database.Finance;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button addItemButton = findViewById(R.id.startButtonHome);
        Button removeItemButton = findViewById(R.id.resetHomeButton);
        Button createDBButton = findViewById(R.id.createDB);
        final TextView centerText = findViewById(R.id.centerText);
        final Finance finance = new Finance(getApplicationContext());
        createDBButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
               finance.addExpense(finance.getExpense(new Expense()));
                Snackbar.make(v, "New row inserted!", Snackbar.LENGTH_LONG).show();
            }
        });
        addItemButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //Code action here.
                String s = "The count is: " + ++counter;
                centerText.setText(s);
            }
        });

        removeItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                Snackbar.make(v, "Counter is reset!", Snackbar.LENGTH_LONG).show();
                String s = "The count is: " + counter;
                centerText.setText(s);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
