package com.intelligence.bootcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText firstname, lastname, phone;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = (EditText) findViewById(R.id.edt_first_name);
        lastname = (EditText) findViewById(R.id.edt_last_name);
        phone = (EditText) findViewById(R.id.edt_phone_number);
        submit = (Button) findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToDB();
            }
        });

    }

    public void sendDataToDB() {
        String url = "http://192.168.137.1/bootcamp/add_user.php";

        final String fname = firstname.getText().toString();
        final String lname = lastname.getText().toString();
        final String phone_number = phone.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(MainActivity.this, s,
                        Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,
                        volleyError.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("firstname", fname);
                params.put("lastname", lname);
                params.put("phone", phone_number);

                return params;
            }
        };

        Volley.newRequestQueue(MainActivity.this).add(request);
    }
}
