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
 *
 * About:
 * This application is built for a personal need and therefor maybe not the most user-friendly. However, if its
 * used in the right way, it's a useful app.
 * I will explain.
 *
 * Every month the 15. i get my scholarship. To prevent use all of it at once, i use this app
 * to map what i SHOULD use today to spend equal amount each day. I can use more. say i spend 500 kr one day,
 * this amount is subtracted from the total and a new vallet/daysleft ratio is calculated. If one day, say i use 0, the
 * new ratio is calculated.
 * This app is not an budget app, that is not the meaning. Its just a guideline for how much i should spend.
 *
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
        todaysMoneyView.setText(limit+ " kr");
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