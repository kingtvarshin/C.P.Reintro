package com.example.cpreintro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class BirdActivity extends AppCompatActivity {

    private String title,observerstr, datestr, latstr, longstr, fixnostr, locationidstr, timestr, azimuthstr,signalstr, commentstr;

    Button save,back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bird_dialog);

        final EditText time = findViewById(R.id.edittime);
        final EditText azimuthbe = findViewById(R.id.editazbe);
        final RadioButton rdnd = findViewById(R.id.radiond);
        final RadioButton rdws = findViewById(R.id.radiows);
        final RadioButton rdss = findViewById(R.id.radioss);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        observerstr = intent.getStringExtra("observer");
        datestr = intent.getStringExtra("date");
        latstr = intent.getStringExtra("latitude");
        longstr = intent.getStringExtra("longitude");
        fixnostr = intent.getStringExtra("fix_no");
        locationidstr = intent.getStringExtra("location_id");
        title = intent.getStringExtra("tag_channel");
        commentstr = intent.getStringExtra("comment");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rdnd.isChecked())
                    signalstr = rdnd.getText().toString();
                else if(rdws.isChecked())
                    signalstr = rdws.getText().toString();
                else if(rdss.isChecked())
                    signalstr = rdss.getText().toString();

                //check for null
                if(latstr == null)
                    latstr = "";
                else if(longstr == null)
                    longstr = "";
                else if(timestr == null)
                    timestr = "";
                timestr = time.getText().toString();
                azimuthstr = azimuthbe.getText().toString();
                addItemToSheet();
            }
        });

    }

    private void addItemToSheet() {

        final ProgressDialog loading = ProgressDialog.show(this,"saving data","Please wait");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxVpHvZ1jIjOsfSXfK0VN61JAGienRqXvsx0Mdr8Bs2ddEgyUc/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addItem");
                parmas.put("Observer",observerstr);
                parmas.put("Date",datestr);
                parmas.put("Tag_Channel",title);
                parmas.put("Time",timestr);
                parmas.put("Latitude",latstr);
                parmas.put("Longitude",longstr);
                parmas.put("Azimuth_Bearing",azimuthstr);
                parmas.put("fix_no",fixnostr);
                parmas.put("Location_id",locationidstr);
                parmas.put("Signal_type",signalstr);
                parmas.put("Comments",commentstr);

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);

    }

    public void showTimePickerDialog(View view) {

        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }
}
