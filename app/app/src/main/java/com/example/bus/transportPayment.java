package com.example.bus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class transportPayment extends AppCompatActivity {

    private TextView mTextMessage;
    private ImageView btnReturn2;


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

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btnReturn2 = findViewById(R.id.btnReturn2);
        btnReturn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(transportPayment.this, transportListDetails.class);
                startActivity(intent_loadCon2);
            }
        });

    }
}
