package com.example.bus;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class index extends AppCompatActivity {

    private TextView mTextMessage, txtDate;
    private Button btnNext1;
    TextView txtUsername;
    Spinner spinnerFrom, spinnerTo;
    ImageButton btnGetDate;
    String DestinationFrom, DestintationTo, DepartureDate, fullname,formattedDate;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent index = new Intent(index.this, index.class);
                    startActivity(index);
                    finish();
                    break;
                case R.id.navigation_dashboard:
                    Intent schedule = new Intent(index.this, userSchedule.class);
                    startActivity(schedule);
                    finish();
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(index.this, accountConst.class);
                    startActivity(account);
                    finish();
                    break;
                case R.id.navigation_payment:
                    Intent payment = new Intent(index.this, payment_tab.class);
                    startActivity(payment);
                    finish();
                    break;
                case R.id.navigation_logout:
                    SharedPreferences sp = getApplication().getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear();
                    editor.putInt("logStatus",0);
                    editor.commit();
                    Intent logout = new Intent(index.this, loadConst.class);
                    startActivity(logout);
                    finish();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        SharedPreferences sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        if(sp.contains("logStatus")) {
            fullname = sp.getString("fullname",null);
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.bringToFront();
        spinnerFrom =  findViewById(R.id.spinFrom);
        spinnerTo =  findViewById(R.id.spinTo);
        txtUsername = findViewById(R.id.indexUsername);
        txtDate = findViewById(R.id.textDate);
        btnGetDate = findViewById(R.id.btnDate);

        Date c = Calendar.getInstance().getTime();
        DateFormat df  = DateFormat.getDateInstance(DateFormat.MEDIUM);
        formattedDate = df.format(c);
        txtDate.setText(formattedDate);

        txtUsername.setText(fullname);

        String[] spinFrom = {"Manila", "San Fernando", "Arayat", "Bataan", "Baguio City", "Dagupan City", "Nueva Ecija", "Clark"};
        String[] spinTo = {"Manila", "San Fernando", "Arayat", "Bataan", "Baguio City", "Dagupan City", "Nueva Ecija", "Clark"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinFrom);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinTo);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        btnNext1 = findViewById(R.id.btnNext1);
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchTrip();
            }
        });

        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        ImageView adsImage = findViewById(R.id.adsImage);
        adsImage.setVisibility(View.INVISIBLE);


        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.animate);

        if (adsImage.getVisibility() == View.INVISIBLE) {
            adsImage.setVisibility(View.VISIBLE);
            adsImage.startAnimation(slideUp);
        }

    }

    private void SearchTrip() {
        DestinationFrom = spinnerFrom.getSelectedItem().toString();
        DestintationTo = spinnerTo.getSelectedItem().toString();

        if(DestinationFrom.isEmpty()){
            Toast.makeText(this,  "Please Select Terminal", Toast.LENGTH_SHORT).show();
        }

        if(DestintationTo.isEmpty()){
            Toast.makeText(this,  "Please Select Drop-off place", Toast.LENGTH_SHORT).show();
        }

        if(!DestinationFrom.isEmpty() && !DestintationTo.isEmpty() && !formattedDate.isEmpty()){
            SharedPreferences sp = getApplication().getSharedPreferences("schedule", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Destination_from",DestinationFrom);
            editor.putString("Destination_to",DestintationTo);
            editor.putString("dateScheduled",formattedDate);
            editor.commit();
            startActivity(new Intent(getApplicationContext(),transportList.class));
            finish();
        }
    }

    private void showDatePicker() {
        Fragment_DatePicker date = new Fragment_DatePicker();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getSupportFragmentManager(),"Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR,year);
            c.set(Calendar.MONTH,monthOfYear);
            c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            DateFormat df  = DateFormat.getDateInstance(DateFormat.MEDIUM);
            formattedDate = df.format(c.getTime());
            txtDate.setText(formattedDate);
        }
    };

}
