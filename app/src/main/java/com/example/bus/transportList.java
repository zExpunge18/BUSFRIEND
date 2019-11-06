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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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

public class transportList extends AppCompatActivity {

    private TextView busPriceTxt;
    private TextView mTextMessage;
    private ImageView btnReturn1;
    private Button testButton;
    ProgressDialog progressDialog;
    String Destination_from, Destination_to, Date;

    List<busList> scheduleList;
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
        SharedPreferences sp = getApplication().getSharedPreferences("schedule", MODE_PRIVATE);
        if(sp.contains("Destination_from")) {
            Destination_from = sp.getString("Destination_from", null);
            Destination_to = sp.getString("Destination_to",null);
        }

        progressDialog = new ProgressDialog(this);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        scheduleList = new ArrayList<>();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        LoadBusList();

//        busLists = new ArrayList<>();
//
//        busLists.add(new busList(R.drawable.busfriend, "AC", "FLorida", "Taguig", "16", "300", "430"))
//        busCustomListAdapter adapter2 = new busCustomListAdapter(this, R.layout.bus_layout_listview, busLists);
//        listView.setAdapter(adapter2);

        listView = findViewById(R.id.busListView);
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

    public void LoadBusList(){
        progressDialog.setMessage("Logging in please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.Load_Buslist,
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
                                getJSONObject(jsonObject.getJSONArray("schedule"));

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
                params.put("Destination_from", Destination_from);
                params.put("Destination_to", Destination_to);
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

    class ScheduleAdapter extends ArrayAdapter<busList>{

        List<busList> scheduleList;

        public ScheduleAdapter(List<busList> scheduleList){
            super(getApplicationContext(), R.layout.bus_layout_listview,scheduleList);
            this.scheduleList = scheduleList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View busListView = inflater.inflate(R.layout.bus_layout_listview, null, true);

            TextView busTypeTxt = busListView.findViewById(R.id.busTypeTxt);
            TextView busNameTxt = busListView.findViewById(R.id.busNameTxt);
            TextView busLocationTxt = busListView.findViewById(R.id.busLocationTxt);
            TextView busSeatTxt = busListView.findViewById(R.id.busSeatTxt);
            TextView busPriceTxt = busListView.findViewById(R.id.busPriceTxt);
            TextView busTimeTxt = busListView.findViewById(R.id.busTimeTxt);

            final busList bus = scheduleList.get(position);

            busTypeTxt.setText(bus.getBusType());
            busNameTxt.setText(bus.getBusName());
            busLocationTxt.setText(bus.getDestination_from());
            busSeatTxt.setText(String.valueOf(bus.getBusSeat()));
            busPriceTxt.setText(String.valueOf(bus.getBusPrice()));
            busTimeTxt.setText(bus.getBusTime());

            return busListView;
        }
    }

    private void getJSONObject(JSONArray schedule) throws JSONException {
        //clearing previous heroes
        scheduleList.clear();

        for (int i = schedule.length()-1; i >= 0; i--) {
            //getting each object
            JSONObject obj = schedule.getJSONObject(i);

            scheduleList.add(new busList(
                    obj.getInt("id"),
                    obj.getInt("busSeat"),
                    obj.getInt("price"),
                    obj.getString("bus_type"),
                    obj.getString("bus_name"),
                    obj.getString("destination_from"),
                    obj.getString("destination_to"),
                    obj.getString("time"),
                    obj.getString("driver_responsible")
            ));
        }

        ScheduleAdapter adapter = new ScheduleAdapter(scheduleList);
        listView.setAdapter(adapter);
    }
}
