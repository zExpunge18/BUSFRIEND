package com.example.bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class index extends AppCompatActivity {

    private TextView mTextMessage;
    private Button btnNext1;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent index = new Intent(index.this, index.class);
                    startActivity(index);
                    break;
                case R.id.navigation_dashboard:
                    Intent schedule = new Intent(index.this, userSchedule.class);
                    startActivity(schedule);
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(index.this, accountConst.class);
                    startActivity(account);
                    break;
                case R.id.navigation_logout:
                    Intent logout = new Intent(index.this, loadConst.class);
                    startActivity(logout);
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

        btnNext1 = findViewById(R.id.btnNext1);
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon4 = new Intent(index.this, transportList.class);
                startActivity(intent_loadCon4);
            }
        });
    }

}
