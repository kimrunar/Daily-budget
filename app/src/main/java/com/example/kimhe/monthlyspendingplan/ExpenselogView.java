package com.example.kimhe.monthlyspendingplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by kimhe on 14.02.2017.
 */

public class ExpenselogView extends AppCompatActivity {
    Databasehandler db = new Databasehandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenselog_view);
        setTitle("Kor mykje har du brukt i dag?");
        db.loadFromPref(this);
    }

    public void saveExpense(View view){
        EditText ed =(EditText) findViewById(R.id.editText2);
        if(TextUtils.isEmpty(ed.getText())) {


            Toast.makeText(this, "Eg må vite $$$ skal eg rekne ut for deg ",
                    Toast.LENGTH_LONG).show();

        }else {
            int expenses = Integer.parseInt(ed.getText().toString());
            db.subtractExpenses(expenses);
            db.saveToPref(this);
            finish();
        }
    }



}
