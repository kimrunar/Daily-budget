package com.example.kimhe.monthlyspendingplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MonthlyInputActivity extends AppCompatActivity {
    EditText monthlySpending;
    DatePicker dueDate;
    Databasehandler db;
    int vallet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_monthly);
    }

    public void myMonthlySpending(View view){

        monthlySpending =(EditText) findViewById(R.id.sum_month);
        dueDate = (DatePicker) findViewById(R.id.daysToNextPayment);

        vallet = textToInt(monthlySpending);
        int   day  = dueDate.getDayOfMonth();
        int   month= dueDate.getMonth();
        int   year = dueDate.getYear();


        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day);
        long calmillies = cal.getTimeInMillis();
        db = new Databasehandler();
        db.setVallet(vallet);
        db.setDueDate(calmillies); //payment date
        db.setDaysLeft();
        db.setTodaysLimit();
        db.saveToPref(this);
        if(TextUtils.isEmpty(monthlySpending.getText())|| dueDate == null) {
            Toast.makeText(this, "Eg må ha $$$",
                    Toast.LENGTH_LONG).show();

        }else{
            finish();
        }
    }

    public int textToInt(EditText text){
        String str = text.getText().toString();
        if(str.isEmpty()){
            return 1;
        }else{
            return Integer.parseInt(text.getText().toString());
        }
    }


}
