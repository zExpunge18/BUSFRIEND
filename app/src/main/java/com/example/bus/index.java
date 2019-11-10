package com.example.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class index extends AppCompatActivity {

    private TextView mTextMessage;
    private Button btnNext1;
    EditText etDestinationFrom, etDestinationTo, etDate;
    String DestinationFrom, DestintationTo, DepartureDate;



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
                case R.id.navigation_logout:
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
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        etDestinationTo = findViewById(R.id.etDestinationTo);
        etDestinationFrom = findViewById(R.id.etDestinationFrom);
        etDate = findViewById(R.id.etDate);

        btnNext1 = findViewById(R.id.btnNext1);
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchTrip();
            }
        });
    }

    private void SearchTrip() {
        DestinationFrom = etDestinationFrom.getText().toString().trim();
        DestintationTo = etDestinationTo.getText().toString().trim();

        if(DestinationFrom.isEmpty()){
            etDestinationFrom.setError("Please enter the Location of Terminal");
            etDestinationFrom.requestFocus();
        }

        if(DestintationTo.isEmpty()){
            etDestinationTo.setError("Please enter the Drop-off location");
            etDestinationTo.requestFocus();
        }

        if(!DestinationFrom.isEmpty() && !DestintationTo.isEmpty()){
            SharedPreferences sp = getApplication().getSharedPreferences("schedule", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Destination_from",DestinationFrom);
            editor.putString("Destination_to",DestintationTo);
            editor.commit();
            startActivity(new Intent(getApplicationContext(),transportList.class));
            finish();
        }
    }

}
