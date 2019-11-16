package com.example.bus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ads_section extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_section);
        btnClose = findViewById(R.id.btnClose);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent indexPage = new Intent(ads_section.this, index.class);
                startActivity(indexPage);
                finish();
            }
        },SPLASH_TIME_OUT );

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ads_section.this, index.class));
                finish();
            }
        });
    }
}
