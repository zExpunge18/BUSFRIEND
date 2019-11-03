package com.example.bus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class transportList extends AppCompatActivity {

    private TextView busPriceTxt;
    private TextView mTextMessage;
    private ImageView btnReturn1;
    private Button testButton;

    List<busList> busLists;
    ListView listView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent index = new Intent(transportList.this, index.class);
                    startActivity(index);
                    break;
                case R.id.navigation_dashboard:
                    Intent schedule = new Intent(transportList.this, userSchedule.class);
                    startActivity(schedule);
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(transportList.this, accountConst.class);
                    startActivity(account);
                    break;
                case R.id.navigation_logout:
                    Intent logout = new Intent(transportList.this, loadConst.class);
                    startActivity(logout);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transport_list_fragment);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        busLists = new ArrayList<>();

        busLists.add(new busList(R.drawable.busfriend, "AC", "FLorida", "Taguig", "16", "300", "430"));

        listView = findViewById(R.id.busListView);

        busCustomListAdapter adapter2 = new busCustomListAdapter(this, R.layout.bus_layout_listview, busLists);
        listView.setAdapter(adapter2);


        busPriceTxt = findViewById(R.id.busPriceTxt);
        btnReturn1 = findViewById(R.id.btnReturn1);


        String[] bustype = {"AC", "Ordinary"};

        Spinner busTypeSpin =  findViewById(R.id.busTypeSpin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bustype);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busTypeSpin.setAdapter(adapter);

        testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(transportList.this, transportListDetails.class);
                startActivity(intent_loadCon2);
            }
        });

        btnReturn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(transportList.this, loadConst.class);
                startActivity(intent_loadCon2);
            }
        });
    }
}
