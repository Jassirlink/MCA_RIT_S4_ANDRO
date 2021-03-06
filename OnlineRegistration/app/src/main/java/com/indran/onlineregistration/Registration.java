package com.indran.onlineregistration;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Registration extends AppCompatActivity {


    AutoCompleteTextView name, email, dob, address, password, cpassword;



    final String PREFS_NAME = "UserFile";
    private DatePicker datePicker;
    private Calendar calendar;

    private int year, month, day;


    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = (AutoCompleteTextView) findViewById(R.id.name);
        email = (AutoCompleteTextView) findViewById(R.id.email);
        dob = (AutoCompleteTextView) findViewById(R.id.dob);
        address = (AutoCompleteTextView) findViewById(R.id.address);

        password = (AutoCompleteTextView) findViewById(R.id.password);

        cpassword = (AutoCompleteTextView) findViewById(R.id.cpassword);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);


        databaseHelper = new DatabaseHelper(Registration.this);

        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    setDate(view);
                    }
            }
        });

    }



    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dob.setText(new StringBuilder().append(year).append("-")
                .append(month).append("-").append(day));
    }

    private boolean requited( AutoCompleteTextView v){
        if (v.getText().toString().matches("")) {
            v.setError("requited");
            return true;
        }
        return  false;
    }


    @SuppressLint("LongLogTag")
    public void registration(View view) {

        if (requited( name))
            return;

        if (requited( email))
            return;

        if (requited( dob))
            return;

        if (requited( address))
            return;
        if (requited( password))
            return;



        if (! cpassword.getText().toString().equals(password.getText().toString())) {
            cpassword.setError("password mismatch ");
            return;
        }


        boolean isInserted = databaseHelper.insertData(name.getText().toString(), email.getText().toString(),
                dob.getText().toString(),
                address.getText().toString(), password.getText().toString() );
        if(isInserted == true){

            Toast.makeText(Registration.this,"Registration successful ",Toast.LENGTH_LONG).show();

//setLogin();

            Intent intent = new Intent( Registration.this, MainActivity.class);

            startActivity(intent);
        }
          else
            Toast.makeText(Registration.this,"invalid data ",Toast.LENGTH_LONG).show();










    }

    private void  setLogin () {
                Cursor res = databaseHelper.getAllData();
        if(res.getCount() == 0) {
            // show message
            Log.d("Error","Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

//            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//            settings.edit().putString("id", res.getString(0)).commit();
            break;
        }


    }
}
