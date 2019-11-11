package com.example.bus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class ads_section extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_section);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent indexPage = new Intent(ads_section.this, index.class);
                startActivity(indexPage);
                finish();
            }
        },SPLASH_TIME_OUT );
    }
}
