package com.example.kimhe.monthlyspendingplan;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

/**
 * Created by kimhe on 13.02.2017.
 */

public class Databasehandler extends AppCompatActivity {
   private int vallet;
    private String limit;
    private long dueDate;
    public  Context AppContext;
    private int daysLeft;

    public Databasehandler(){

    }




    public void setDaysLeft() {

        if(dueDate == 0){
            System.out.println("");
        }else {

            long msDiff = dueDate - System.currentTimeMillis() ;
            daysLeft = (int) TimeUnit.MILLISECONDS.toDays(msDiff);

        }
    }






    public  String getTodaysLimit() {
        return limit;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setTodaysLimit() {
       int vallet = getVallet();
        if(vallet != 0 && daysLeft != 0) {
            int lmit = vallet / (daysLeft+1);
            limit = Integer.toString(lmit);
        }else{
            limit = Integer.toString(vallet);
        }
    }

    public int getVallet() {
        return vallet;
    }

    public  void setVallet(int vallet) {
        this.vallet = vallet;
    }


    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public void saveToPref(Context context){
        AppContext = context;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(AppContext);
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("sum_monthly",vallet);
        edit.putLong("days_left", dueDate);
        edit.apply();
    }

    public void loadFromPref(Context context){
        AppContext = context;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(AppContext);
        vallet = pref.getInt("sum_monthly",1);
        if( pref.getLong("days_left",1 ) != 0) {
            dueDate= pref.getLong("days_left", 1);
        }else{
            dueDate = 1;
        }
        setVallet(vallet);
        setDaysLeft();
        setTodaysLimit();
    }

    public void subtractExpenses(int expense){
        setVallet(vallet - expense);

    }


}
