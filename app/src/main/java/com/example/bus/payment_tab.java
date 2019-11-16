package com.example.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class payment_tab extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent index = new Intent(payment_tab.this, index.class);
                    startActivity(index);
                    finish();
                    break;
                case R.id.navigation_dashboard:
                    Intent schedule = new Intent(payment_tab.this, userSchedule.class);
                    startActivity(schedule);
                    finish();
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(payment_tab.this, accountConst.class);
                    startActivity(account);
                    finish();
                    break;
                case R.id.navigation_payment:
                    Intent payment = new Intent(payment_tab.this, payment_tab.class);
                    startActivity(payment);
                    finish();
                    break;
                case R.id.navigation_logout:
                    SharedPreferences sp = getApplication().getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear();
                    editor.putInt("logStatus",0);
                    editor.commit();
                    Intent logout = new Intent(payment_tab.this, loadConst.class);
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
        setContentView(R.layout.activity_payment_tab);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.bringToFront();
    }
}
