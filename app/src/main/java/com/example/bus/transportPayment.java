package com.example.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class transportPayment extends AppCompatActivity {

    private TextView mTextMessage, txtName, txtMobile, txtPrice, txtEwallet, txtEmail;
    private ImageView btnReturn2;
    private Button btnReceipt;
    int busPrice, ewallet;
    String fullname, email, mobile;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent index = new Intent(transportPayment.this, index.class);
                    startActivity(index);
                    break;
                case R.id.navigation_dashboard:
                    Intent schedule = new Intent(transportPayment.this, userSchedule.class);
                    startActivity(schedule);
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(transportPayment.this, accountConst.class);
                    startActivity(account);
                    break;
                case R.id.navigation_logout:
                    Intent logout = new Intent(transportPayment.this, loadConst.class);
                    startActivity(logout);
                    break;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_payment);

        SharedPreferences sp2 = getApplication().getSharedPreferences("trip_details", MODE_PRIVATE);
        SharedPreferences sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        busPrice = sp2.getInt("price",0);
        fullname = sp.getString("fullname",null);
        mobile = sp.getString("mobile",null);
        email = sp.getString("email",null);
        ewallet = sp.getInt("ewallet",0);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtMobile = findViewById(R.id.txtMobile);
        txtPrice = findViewById(R.id.txtPrice);
        txtEwallet = findViewById(R.id.txtEwallet);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.bringToFront();

        txtName.setText(fullname);
        txtEmail.setText(email);
        txtMobile.setText(mobile);
        txtPrice.setText(String.valueOf(busPrice));
        txtEwallet.setText(String.valueOf(ewallet));

        btnReturn2 = findViewById(R.id.btnReturn2);
        btnReturn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(transportPayment.this, transportListDetails.class);
                startActivity(intent_loadCon2);
                finish();
            }
        });

        btnReceipt = findViewById(R.id.btnReceipt);
        btnReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon3 = new Intent(transportPayment.this, transportReceipt.class);
                startActivity(intent_loadCon3);
                finish();
            }
        });

    }
}
