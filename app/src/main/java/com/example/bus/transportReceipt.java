package com.example.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class transportReceipt extends AppCompatActivity {

    Button btnHome;
    int id, payment, booked_seat, price;
    String fullname, destinationFrom, destinationTo, date, bus_name, email;
    TextView txtBusName, txtDestination, txtQty, txtDate, txtFullname, txtEmail, txtPrice, txtTicketNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_receipt);
        SharedPreferences sp = getApplication().getSharedPreferences("trip_details", MODE_PRIVATE);
        id = sp.getInt("id",0);
        payment = sp.getInt("payment",0);
        booked_seat = sp.getInt("booked_seat",0);
        price = sp.getInt("price",0);
        fullname = sp.getString("fullname", null);
        destinationFrom = sp.getString("destinationFrom", null);
        destinationTo = sp.getString("destinationTo", null);
        date = sp.getString("date", null);
        bus_name = sp.getString("bus_name", null);
        email = sp.getString("email", null);

        txtBusName = findViewById(R.id.receiptBusNum);
        txtDestination = findViewById(R.id.receiptDes);
        txtQty = findViewById(R.id.receiptQuan);
        txtDate = findViewById(R.id.receiptDate);
        txtFullname = findViewById(R.id.receiptName);
        txtEmail = findViewById(R.id.receiptEmail);
        txtPrice = findViewById(R.id.receiptPrice);
        txtTicketNo = findViewById(R.id.receiptTicketNum);

        txtBusName.setText(bus_name);
        txtTicketNo.setText(String.valueOf(id));
        txtPrice.setText(String.valueOf(price));
        txtEmail.setText(email);
        txtDate.setText(date);
        txtQty.setText(String.valueOf(booked_seat));
        txtFullname.setText(fullname);
        txtDestination.setText(destinationTo);

        btnHome = findViewById(R.id.btnRrturnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(transportReceipt.this, loadConst.class));
                finish();
            }
        });
    }
}
