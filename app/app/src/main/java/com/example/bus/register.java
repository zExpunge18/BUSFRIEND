package com.example.bus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    ImageView btnBack;
    Button btnRegister;
    EditText etName, etMobile, etEmail, etPassword, etCpasswod;
    private static final int CODE_POST_REQUEST = 1025;
    ProgressDialog progressDialog;

    String fullname, mobile, email, password, cpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etFullname);
        etCpasswod = findViewById(R.id.etConfirmPassword);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnBack = findViewById(R.id.btnBack);
        btnRegister = findViewById(R.id.btnRegister);
        progressDialog = new ProgressDialog(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(register.this, loadConst.class);
                startActivity(intent_loadCon2);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_loadCon2 = new Intent(register.this, index.class);
                startActivity(intent_loadCon2);
            }
        });

        /*btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });*/
    }

    /*private void RegisterUser() {

        fullname = etName.getText().toString();
        mobile = etMobile.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        cpassword = etCpasswod.getText().toString();

        Toast.makeText(this, fullname + " " + mobile + " " + email +" " + password + " " + cpassword, Toast.LENGTH_SHORT).show();

        if(fullname.isEmpty()){
            etName.setError("Please enter your Fullname");
            etName.requestFocus();
        }

        if(mobile.isEmpty()){
            etMobile.setError("Please enter your Mobile Number");
            etMobile.requestFocus();
        }else if(mobile.length() < 11){
            etMobile.setError("Mobile Number length should be 11 digits");
            etMobile.requestFocus();
        }

        if(email.isEmpty()){
            etEmail.setError("Please enter your E-mail Address");
            etEmail.requestFocus();
        }

        if(password.isEmpty()){
            etPassword.setError("Please enter your Password");
            etPassword.requestFocus();
        }

        if(cpassword.isEmpty()){
            etCpasswod.setError("Please enter your Confirmation Password");
            etCpasswod.requestFocus();
        }

        if(!password.equals(cpassword)){
            etPassword.setError("Please your Password and Confirmation Password didn't match");
            etPassword.requestFocus();
        }

        if(!fullname.isEmpty() && password.equals(cpassword) && !cpassword.isEmpty() && !password.isEmpty() && !email.isEmpty() && mobile.length() >= 11){

            progressDialog.setMessage("Registering user please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.REGSITER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean error = jsonObject.getBoolean("error");
                                //String message = jsonObject.getString("message");

                                if(!error){
                                    Toast.makeText(getApplicationContext(),"Account Successfully Created!",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),loadConst.class));
                                    finish();
                                }


                            }catch (JSONException e){

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
                    params.put("fullname", fullname);
                    params.put("mobile", mobile);
                    params.put("email", email);
                    params.put("password", password);
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

    }*/
}
