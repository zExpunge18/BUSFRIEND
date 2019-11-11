package com.example.bus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class loadConst extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView btnRegister;
    ProgressDialog progressDialog;
    String Email, Password, fullname, mobile, email;
    int ewallet, logStatus, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_conts);

        SharedPreferences sp = getApplication().getSharedPreferences("user", MODE_PRIVATE);
        if(sp.contains("logStatus")) {
            logStatus = sp.getInt("logStatus", 0);
        }

        if(logStatus == 1){
            startActivity(new Intent(getApplicationContext(), index.class));
            finish();
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLoadNxt);
        btnRegister = findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register.class));
                finish();
            }
        });

    }

    private void LoginUser() {
        Email = etEmail.getText().toString();
        Password = etPassword.getText().toString();

        if(Email.isEmpty()){
            etEmail.setError("Please enter your E-mail Address");
            etEmail.requestFocus();
        }

        if(Password.isEmpty()){
            etPassword.setError("Please enter your Password");
            etPassword.requestFocus();
        }

        if(!Email.isEmpty() && !Password.isEmpty()){
            progressDialog.setMessage("Logging in please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean error = jsonObject.getBoolean("error");
                                String message = jsonObject.getString("message");

                                if(!error){
                                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                                    getJSONObject(jsonObject.getJSONArray("user"));
                                    startActivity(new Intent(getApplicationContext(),ads_section.class));
                                    finish();
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
                    params.put("email", Email);
                    params.put("password", Password);
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
    }

    private void getJSONObject(JSONArray user) throws JSONException {
        for (int i = 0; i < user.length(); i++) {
            JSONObject obj = user.getJSONObject(i);

            fullname = obj.getString("fullname");
            email = obj.getString("email");
            mobile = obj.getString("contact_no");
            ewallet = obj.getInt("ewallet");
            id = obj.getInt("id");

            SharedPreferences sp = getApplication().getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("logStatus",1);
            editor.putInt("id", id);
            editor.putString("fullname",fullname);
            editor.putString("email",email);
            editor.putString("mobile",mobile);
            editor.putInt("ewallet",ewallet);
            editor.commit();

        }
    }
}
