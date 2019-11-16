package com.example.bus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class transportListDetails extends AppCompatActivity {

    private Button btnCancelBtn;
    private TextView mTextMessage, txtBusname, txtOperator, txtPlateNo, txtBusType, txtDestination, txtDate, txtTime, txtPrice;
    private Button btnPayment;
    ProgressDialog progressDialog;
    int busPrice,tripID,userID, ewallet, payment, busSeat, quantity, fBusPrice;
    EditText etQuantity;
    String name, operator, plateNo, busType, destination, date, driver_responsible, time;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent index = new Intent(transportListDetails.this, index.class);
                    startActivity(index);
                    finish();
                    break;
                case R.id.navigation_dashboard:
                    Intent schedule = new Intent(transportListDetails.this, userSchedule.class);
                    startActivity(schedule);
                    finish();
                    break;
                case R.id.navigation_account:
                    Intent account = new Intent(transportListDetails.this, accountConst.class);
                    startActivity(account);
                    finish();
                    break;
                case R.id.navigation_payment:
                    Intent payment = new Intent(transportListDetails.this, payment_tab.class);
                    startActivity(payment);
                    finish();
                    break;
                case R.id.navigation_logout:
                    SharedPreferences sp = getApplication().getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear();
                    editor.putInt("logStatus",0);
                    editor.commit();
                    Intent logout = new Intent(transportListDetails.this, loadConst.class);
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
        setContentView(R.layout.activity_transport_list_details);

        SharedPreferences sp = getApplication().getSharedPreferences("trip_details", MODE_PRIVATE);
            tripID = sp.getInt("id",0);
            driver_responsible = sp.getString("driver_responsible", null);
            name = sp.getString("bus_name", null);
            operator = sp.getString("operator", null);
            plateNo = sp.getString("plate_no", null);
            busPrice = sp.getInt("price",0);
            fBusPrice= sp.getInt("price",0);
            destination = sp.getString("destination_To",null);
            date = sp.getString("date",null);
            busType = sp.getString("busType",null);
            time = sp.getString("time",null);
            busSeat = sp.getInt("busSeat", 0);
        SharedPreferences sp2 = this.getSharedPreferences("user", Context.MODE_PRIVATE);
            userID = sp2.getInt("id", 0);
            ewallet = sp2.getInt("ewallet", 0);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        txtBusname = findViewById(R.id.busNmDetailsTxt);
        txtOperator = findViewById(R.id.busOperateTxt);
        txtPlateNo = findViewById(R.id.busPlateTxt);
        txtBusType = findViewById(R.id.busTypeDetTxt);
        txtDestination = findViewById(R.id.busDestinationTxt);
        txtDate = findViewById(R.id.busTransDateTxt);
        txtTime = findViewById(R.id.busTransTimeTxt);
        txtPrice = findViewById(R.id.txtPrice);
        progressDialog = new ProgressDialog(this);
        etQuantity = findViewById(R.id.busQuanTxt);


        btnCancelBtn = findViewById(R.id.busCancelBtn);

        btnCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(transportListDetails.this, transportList.class);
                startActivity(intent_loadCon2);
                finish();
            }
        });

        btnPayment = findViewById(R.id.btnPayment);

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayTicket();
            }
        });

        txtBusname.setText(name);
        txtOperator.setText(operator);
        txtPlateNo.setText(plateNo);
        txtDate.setText(date);
        txtBusType.setText(busType);
        txtDestination.setText(destination);
        txtDate.setText(date);
        txtTime.setText(time);
        txtPrice.setText("PHP " + busPrice);

    }

    public void PayTicket() {
        String etQty = etQuantity.getText().toString().trim();

        if (etQty.isEmpty()) {
            etQuantity.setError("Please Enter the number of tickets to be reserved");
            etQuantity.requestFocus();
        } else {

            quantity = Integer.parseInt(etQty);
            fBusPrice = ((fBusPrice* quantity) + 50);
            Toast.makeText(this, String.valueOf(fBusPrice), Toast.LENGTH_LONG).show();

            if(busSeat < quantity){
                etQuantity.setError("You have Exceeded the number of Passengers Please look for another trip!");
                etQuantity.requestFocus();
            }

            if (!etQty.isEmpty() && ewallet >= fBusPrice && busSeat >=quantity ) {

                payment = ewallet - fBusPrice;
                busSeat = busSeat - quantity;

                progressDialog.setMessage("Booking your Ticket please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.Book_Ticket,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    boolean error = jsonObject.getBoolean("error");
                                    String message = jsonObject.getString("message");
                                    if (!error) {
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                        getJSONObject(jsonObject.getJSONArray("user"));
                                        SharedPreferences sp = getApplication().getSharedPreferences("reciept", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.clear();
                                        editor.putInt("payment", fBusPrice);
                                        editor.putInt("currentAmount", payment);
                                        editor.putInt("quantity", quantity);
                                        editor.commit();

                                        startActivity(new Intent(transportListDetails.this, transportPayment.class));
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    }


                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("tripID", String.valueOf(tripID));
                        params.put("userID", String.valueOf(userID));
                        params.put("payment", String.valueOf(busPrice));
                        params.put("ewallet", String.valueOf(payment));
                        params.put("busSeat", String.valueOf(busSeat));
                        params.put("quantity", String.valueOf(quantity));
                        return params;
                    }
                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }
            if (ewallet < fBusPrice) {
                Toast.makeText(this, "Insufficient Balance please Loadup at the nearest Terminal", Toast.LENGTH_LONG).show();
                fBusPrice = busPrice;
            }
        }
    }

    private void getJSONObject(JSONArray user)  throws JSONException {
        for (int i = 0; i < user.length(); i++) {
            JSONObject obj = user.getJSONObject(i);

            ewallet = obj.getInt("ewallet");

            SharedPreferences sp = getApplication().getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("ewallet",payment);
            editor.commit();

        }
    }
}
