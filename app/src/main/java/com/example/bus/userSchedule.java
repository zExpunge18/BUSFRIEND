package com.example.bus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userSchedule extends AppCompatActivity {

    private TextView mTextMessage, txtDestinationFrom, txtDestinationTo, txtDate, txtBusPrice, txtBusName;
    ListView tripHistoryListView;
    List<TransactionHistory> scheduleList;
    ProgressDialog progressDialog;
    int userID;


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

        SharedPreferences sp = getApplication().getSharedPreferences("user", MODE_PRIVATE);
        userID = sp.getInt("id",0);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        tripHistoryListView = findViewById(R.id.tripHistoryListView);
        progressDialog = new ProgressDialog(this);
        scheduleList = new ArrayList<>();

        LoadTransactionHistory();

        tripHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final TransactionHistory trip = scheduleList.get(position);
                SharedPreferences sp = getApplication().getSharedPreferences("trip_details", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.putInt("id",trip.getId());
                editor.putInt("payment",trip.getPayment());
                editor.putInt("booked_seat", trip.getBooked_seat());
                editor.putInt("price", trip.getPrice());
                editor.putString("fullname", trip.getFullname());
                editor.putString("destinationFrom", trip.getDestinationFrom());
                editor.putString("destinationTo",trip.getDestinationTo());
                editor.putString("date",trip.getDate());
                editor.putString("bus_name",trip.getBus_name());
                editor.putString("email",trip.getEmail());
                editor.commit();
                startActivity(new Intent(getApplicationContext(),transportReceipt.class));
                finish();
            }
        });
    }

    public void LoadTransactionHistory(){
        Toast.makeText(this,String.valueOf(userID),Toast.LENGTH_LONG).show();
        progressDialog.setMessage("Loading Available Bus in please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://sylas123.000webhostapp.com/mobile/LoadTransactionHistory",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean error = jsonObject.getBoolean("error");
                            String message = jsonObject.getString("message");

                            if(!error){
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                getJSONObject(jsonObject.getJSONArray("tripCredentials"));

                            }else {
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                            }


                        }catch (JSONException e){
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                HashMap<String, String> params = new HashMap<>();
                params.put("userID", String.valueOf(userID));
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    class ScheduleAdapter extends ArrayAdapter<TransactionHistory> {

        List<TransactionHistory> scheduleList;

        public ScheduleAdapter(List<TransactionHistory> scheduleList){
            super(getApplicationContext(), R.layout.bus_layout_listview,scheduleList);
            this.scheduleList = scheduleList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View busListView = inflater.inflate(R.layout.trip_layout_listview, null, true);
                txtBusName = busListView.findViewById(R.id.busPriceTxt);
                txtBusPrice = busListView.findViewById(R.id.busTimeTxt);
                txtDate = busListView.findViewById(R.id.txtDate);
                txtDestinationFrom = busListView.findViewById(R.id.txtDestination);
                txtDestinationTo = busListView.findViewById(R.id.txtDestinationTo);


            final TransactionHistory trip = scheduleList.get(position);

            txtBusName.setText(trip.getBus_name());
            txtBusPrice.setText(String.valueOf(trip.getPayment()));
            txtDate.setText(trip.getDate());
            txtDestinationFrom.setText(String.valueOf(trip.getDestinationFrom()));
            txtDestinationTo.setText(String.valueOf(trip.getDestinationTo()));

            return busListView;
        }
    }

    private void getJSONObject(JSONArray schedule) throws JSONException {
        //clearing previous heroes
        scheduleList.clear();

        for (int i = schedule.length()-1; i >= 0; i--) {
            //getting each object
            JSONObject obj = schedule.getJSONObject(i);

            scheduleList.add(new TransactionHistory(
                    obj.getInt("id"), //id
                    obj.getInt("payment"), //payment
                    obj.getInt("booked_seat"), // booked_seat
                    obj.getInt("price"), // price
                    obj.getString("fullname"), //fullname
                    obj.getString("destination_from"), //destinationFrom
                    obj.getString("destination_to"), // destinationTo
                    obj.getString("date_booked"), //date
                    obj.getString("bus_name"), //bus_name
                    obj.getString("email") //email
            ));
        }

        userSchedule.ScheduleAdapter adapter = new userSchedule.ScheduleAdapter(scheduleList);
        tripHistoryListView.setAdapter(adapter);
    }
}
