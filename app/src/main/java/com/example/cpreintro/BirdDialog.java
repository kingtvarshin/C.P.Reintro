package com.example.cpreintro;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

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

public class BirdDialog extends AppCompatDialogFragment {

    private String title,observerstr, datestr, latstr, longstr, fixnostr, locationidstr, timestr, azimuthstr;
    private EditText time;
    private EditText azimuthbe;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.bird_dialog,null);

        if (getArguments() != null) {
            title = getArguments().getString("title","");
            observerstr = getArguments().getString("observer","");
            datestr = getArguments().getString("date","");
            latstr = getArguments().getString("lat","");
            longstr = getArguments().getString("long","");
            fixnostr = getArguments().getString("fixno","");
            locationidstr = getArguments().getString("locationid","");
        }

        time = view.findViewById(R.id.edittime);
        azimuthbe = view.findViewById(R.id.editazbe);

        timestr = time.getText().toString();
        azimuthstr = azimuthbe.getText().toString();


        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(view.getContext(),"Selected: " +"cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addItemToSheet();
                        Toast.makeText(view.getContext(),"Selected: " +" save " + title +
                                observerstr + datestr + latstr+ longstr+fixnostr+locationidstr, Toast.LENGTH_LONG).show();
                    }
                });



        return builder.create();
    }

    private void addItemToSheet() {

        final ProgressDialog loading = ProgressDialog.show(getContext(),"saving data","Please wait");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.gs_script),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();

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
//                parmas.put("Signal Type",);

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(getContext());

        queue.add(stringRequest);

    }
}
