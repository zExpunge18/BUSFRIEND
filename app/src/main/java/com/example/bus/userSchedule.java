package com.example.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class userSchedule extends AppCompatActivity {

    private TextView mTextMessage;
    ListView tripHistoryListView;
    List<busList> scheduleList;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent index = new Intent(userSchedule.this, index.class);
                    startActivity(index);
                    finish();
                    break;
                case R.id.navigation_dashboard:
                    Intent schedule = new Intent(userSchedule.this, userSchedule.class);
                    startActivity(schedule);
                    finish();
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(userSchedule.this, accountConst.class);
                    startActivity(account);
                    finish();
                    break;
                case R.id.navigation_logout:
                    SharedPreferences sp = getApplication().getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear();
                    editor.putInt("logStatus",0);
                    editor.commit();
                    Intent logout = new Intent(userSchedule.this, loadConst.class);
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
        setContentView(R.layout.activity_user_schedule);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        tripHistoryListView = findViewById(R.id.tripHistoryListView);
    }
}
