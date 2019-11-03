package com.example.bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class transportListDetails extends AppCompatActivity {

    private Button btnCancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_list_details);



        btnCancelBtn = findViewById(R.id.busCancelBtn);

        btnCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(transportListDetails.this, transportList.class);
                startActivity(intent_loadCon2);
            }
        });
    }
}
