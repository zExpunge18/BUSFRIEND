package com.example.bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class transportList extends AppCompatActivity {

    private TextView busPriceTxt;
    private ImageView btnReturn1;

    List<busList> busLists;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transport_list_fragment);

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


       /*busPriceTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(transportList.this, transportListDetails.class);
                startActivity(intent_loadCon2);
            }
        });*/

        btnReturn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(transportList.this, loadConst.class);
                startActivity(intent_loadCon2);
            }
        });
    }
}
