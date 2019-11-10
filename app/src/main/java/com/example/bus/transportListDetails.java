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
import android.widget.TextView;

public class transportListDetails extends AppCompatActivity {

    private Button btnCancelBtn;
    private TextView mTextMessage, txtBusname, txtOperator, txtPlateNo, txtBusType, txtDestination, txtDate, txtTime, txtPrice;
    private Button btnPayment;
    int busPrice;
    String name, operator, plateNo, busType, destination, date, driver_responsible, time, price;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent index = new Intent(transportListDetails.this, index.class);
                    startActivity(index);
                    break;
                case R.id.navigation_dashboard:
                    Intent schedule = new Intent(transportListDetails.this, userSchedule.class);
                    startActivity(schedule);
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(transportListDetails.this, accountConst.class);
                    startActivity(account);
                    break;
                case R.id.navigation_logout:
                    Intent logout = new Intent(transportListDetails.this, loadConst.class);
                    startActivity(logout);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_list_details);

        SharedPreferences sp = getApplication().getSharedPreferences("trip_details", MODE_PRIVATE);
            driver_responsible = sp.getString("driver_responsible", null);
            name = sp.getString("bus_name", null);
            operator = sp.getString("operator", null);
            plateNo = sp.getString("plate_no", null);
            busPrice = sp.getInt("price",0);
            destination = sp.getString("destination_To",null);
            date = sp.getString("date",null);
            busType = sp.getString("busType",null);
            time = sp.getString("time",null);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        txtBusname = findViewById(R.id.busNmDetailsTxt);
        txtOperator = findViewById(R.id.busOperateTxt);
        txtPlateNo = findViewById(R.id.busPlateTxt);
        txtBusType = findViewById(R.id.busTypeDetTxt);
        txtDestination = findViewById(R.id.busDestinationTxt);
        txtDate = findViewById(R.id.busTransDateTxt);
        txtTime = findViewById(R.id.busTransTimeTxt);
        txtPrice = findViewById(R.id.txtPrice);


        btnCancelBtn = findViewById(R.id.busCancelBtn);

        btnCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(transportListDetails.this, transportList.class);
                startActivity(intent_loadCon2);
                finish();
            }
        });

        btnPayment = findViewById(R.id.btnPayment);

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayTicket();
            }
        });

        txtBusname.setText(name);
        txtOperator.setText(operator);
        txtPlateNo.setText(plateNo);
        txtDate.setText(date);
        txtBusType.setText(busType);
        txtDestination.setText(destination);
        txtDate.setText(date);
        txtTime.setText(time);
        txtPrice.setText("PHP " + busPrice);

    }

    public void PayTicket(){

        Intent intent_loadCon2 = new Intent(transportListDetails.this, transportPayment.class);
        startActivity(intent_loadCon2);
        finish();

    }
}
