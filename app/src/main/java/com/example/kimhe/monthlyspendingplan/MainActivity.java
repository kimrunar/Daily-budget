package com.example.kimhe.monthlyspendingplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kimhe on 13.02.2017.
 */

public class MainActivity extends AppCompatActivity {
    Databasehandler db = new Databasehandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My daily vallet");
        update(null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public  void update(View view){
        TextView todaysMoneyView = (TextView) findViewById(R.id.todaysLimit);
        db.loadFromPref(this);
        String limit = db.getTodaysLimit();
        todaysMoneyView.setText(limit);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.update_budget) {
            Intent intent = new Intent(this, MonthlyInputActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void mAddexpenseActivity(View view){
        Intent intent = new Intent(this, ExpenselogView.class);
        startActivity(intent);
    }


}