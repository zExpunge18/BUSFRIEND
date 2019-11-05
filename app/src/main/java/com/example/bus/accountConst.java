package com.example.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class accountConst extends AppCompatActivity {

    private TextView mTextMessage;
    EditText etName, etMobile, etEmail;
    String fullname, mobile, email;
    int ewallet;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent index = new Intent(accountConst.this, index.class);
                    startActivity(index);
                    
                    break;
                case R.id.navigation_dashboard:
                    Intent schedule = new Intent(accountConst.this, userSchedule.class);
                    startActivity(schedule);
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(accountConst.this, accountConst.class);
                    startActivity(account);
                    break;
                case R.id.navigation_logout:
                    Intent logout = new Intent(accountConst.this, loadConst.class);
                    startActivity(logout);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_const);

        SharedPreferences sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        if(sp.contains("logStatus")) {
            fullname = sp.getString("fullname",null);
            mobile = sp.getString("mobile",null);
            email = sp.getString("email",null);
            ewallet = sp.getInt("ewallet",0);
        }

        etName = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etContact);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        etName.setText(fullname);
        etMobile.setText(mobile);
        etEmail.setText(email);
    }

}
