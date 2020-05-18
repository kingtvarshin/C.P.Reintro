package com.example.cpreintro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.*;

public class MainActivity extends AppCompatActivity {

    EditText datetxt, lattxt, longtxt, fixnotxt, obs1, obs2, lat, lon, latdeg, latmin, latsec, longdeg, longmin, longsec, comment;
    String observerstr, datestr, latstr, longstr, fixnostr, locationidstr, commentstr;
    String temp = null;
    Switch latlongunit;
    Spinner pointname;
    ToggleButton toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //date
        refreshdate();

        //point name or coordinates
        toggle = findViewById(R.id.point_or_coord);
        latlongunit = findViewById(R.id.switchlatlong);
        pointname = findViewById(R.id.point_name);
        lat = findViewById(R.id.latitude);
        lon = findViewById(R.id.longitude);
        latdeg = findViewById(R.id.latitudedegree);
        latmin = findViewById(R.id.latitudemin);
        latsec = findViewById(R.id.latitudesec);
        longdeg = findViewById(R.id.longitudedegree);
        longmin = findViewById(R.id.longitudemin);
        longsec = findViewById(R.id.longitudesec);

        //point name dropdown
        List<String> pointnamelist = new ArrayList<>();
        pointnamelist.add(0, "Select a Point name from the List");
        pointnamelist.add("dead_point");
        pointnamelist.add("way_point");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pointnamelist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pointname.setAdapter(arrayAdapter);
        pointname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (parent.getItemAtPosition(position).equals("Select a Point name from the List"))
                {
                    locationidstr = "";
                }
                else
                {
                    locationidstr = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //initial visibility
        lat.setVisibility(View.GONE);
        lon.setVisibility(View.GONE);
        latdeg.setVisibility(View.GONE);
        latmin.setVisibility(View.GONE);
        latsec.setVisibility(View.GONE);
        longdeg.setVisibility(View.GONE);
        longmin.setVisibility(View.GONE);
        longsec.setVisibility(View.GONE);
        latlongunit.setVisibility(View.GONE);
        pointname.setVisibility(View.VISIBLE);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    pointname.setSelection(0);
                    lat.setVisibility(View.VISIBLE);
                    lon.setVisibility(View.VISIBLE);
                    latlongunit.setVisibility(View.VISIBLE);
                    latlongunit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(b)
                            {
                                lat.setText("");
                                lon.setText("");
                                lat.setVisibility(View.GONE);
                                lon.setVisibility(View.GONE);
                                latdeg.setVisibility(View.VISIBLE);
                                latmin.setVisibility(View.VISIBLE);
                                latsec.setVisibility(View.VISIBLE);
                                longdeg.setVisibility(View.VISIBLE);
                                longmin.setVisibility(View.VISIBLE);
                                longsec.setVisibility(View.VISIBLE);

                            }
                            else
                            {
                                latdeg.setText("");
                                latmin.setText("");
                                latsec.setText("");
                                longdeg.setText("");
                                longmin.setText("");
                                longsec.setText("");
                                lat.setVisibility(View.VISIBLE);
                                lon.setVisibility(View.VISIBLE);
                                latdeg.setVisibility(View.GONE);
                                latmin.setVisibility(View.GONE);
                                latsec.setVisibility(View.GONE);
                                longdeg.setVisibility(View.GONE);
                                longmin.setVisibility(View.GONE);
                                longsec.setVisibility(View.GONE);
                            }
                        }
                    });
                    pointname.setVisibility(View.GONE);
                }
                else
                {
                    latlongunit.setChecked(false);
                    latdeg.setText("");
                    latmin.setText("");
                    latsec.setText("");
                    longdeg.setText("");
                    longmin.setText("");
                    longsec.setText("");
                    lat.setText("");
                    lon.setText("");
                    lat.setVisibility(View.GONE);
                    lon.setVisibility(View.GONE);
                    latdeg.setVisibility(View.GONE);
                    latmin.setVisibility(View.GONE);
                    latsec.setVisibility(View.GONE);
                    longdeg.setVisibility(View.GONE);
                    longmin.setVisibility(View.GONE);
                    longsec.setVisibility(View.GONE);
                    pointname.setVisibility(View.VISIBLE);
                    latlongunit.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        refreshdate();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        refreshdate();
        super.onRestart();
    }

    public void refreshdate() {
        Calendar cal = getInstance();
        datetxt = findViewById(R.id.datetxt);
        datetxt.setText(cal.get(DATE)+"/"+cal.get(MONTH)+"/"+cal.get(YEAR));
    }

    //bird dialog onclick
    public void birddialog(View view) {

        //editfield decelerations
        datetxt = findViewById(R.id.datetxt);
        lattxt = findViewById(R.id.latitude);
        longtxt = findViewById(R.id.longitude);
        fixnotxt = findViewById(R.id.fix_no);
        latdeg = findViewById(R.id.latitudedegree);
        latmin = findViewById(R.id.latitudemin);
        latsec = findViewById(R.id.latitudesec);
        longdeg = findViewById(R.id.longitudedegree);
        longmin = findViewById(R.id.longitudemin);
        longsec = findViewById(R.id.longitudesec);
        comment = findViewById(R.id.commnets);

        //empty field check
        if(observerstr != null)
            observerstr = observerstr.trim();
        if(datetxt.getText().toString().equals("")
                || fixnotxt.getText().toString().equals("")
                || observerstr == null
                || observerstr.equals(""))
        {
            Toast.makeText(view.getContext(),"please fill the empty fields", Toast.LENGTH_SHORT).show();
            return ;
        }

        Intent intent = new Intent(this, BirdActivity.class);

        //observer string passed
        String editobserver = observeredittextstr();
        if(observerstr == null)
        {
            if (editobserver != null) {
                observerstr = editobserver;
            }
        }
        else
        {
            if(editobserver == null)
            {
                if(temp != null && observerstr.contains(temp))
                {
                    String tempword = temp + " ";
                    observerstr = observerstr.replaceAll(tempword,"");
                    tempword = " " + temp;
                    observerstr = observerstr.replaceAll(tempword,"");
                    tempword = temp;
                    observerstr = observerstr.replaceAll(tempword,"");
                }
            }
            else
            {
                if (!observerstr.contains(editobserver)) {
                    observerstr = observerstr.concat(" "+editobserver);
                }
            }
        }
        temp = editobserver;
        intent.putExtra("observer",observerstr);

        //date string passed
        if (datetxt.getText() != null) {
            datestr = datetxt.getText().toString();
        }
        intent.putExtra("date",datestr);

        //comment string passed
        if (comment.getText() != null) {
            commentstr = comment.getText().toString();
        }
        intent.putExtra("comment",commentstr);

        //tag channel string passed
        switch(view.getId())
        {
            case R.id.bird00:
                intent.putExtra("tag_channel","bird : 00");
                break;
            case R.id.bird25:
                intent.putExtra("tag_channel", "bird : 25");
                break;
            case R.id.bird40:
                intent.putExtra("tag_channel", "bird : 40");
                break;
            case R.id.bird45:
                intent.putExtra("tag_channel", "bird : 45");
                break;
        }

        //lat transfer
        if (!lattxt.getText().toString().equals("")) {
            latstr = lattxt.getText().toString();
            intent.putExtra("latitude",latstr);
        }
        else if (!latdeg.getText().toString().equals("")) {
            String temp = latdeg.getText().toString() + "°" + latmin.getText().toString() + "'" + latsec.getText().toString() + "\"";
            latstr = temp ;
            intent.putExtra("latitude",latstr);
        }
        else {
            latstr = "";
        }

        //long transfer
        if (!longtxt.getText().toString().equals("")) {
            longstr = longtxt.getText().toString();
            intent.putExtra("longitude",longstr);
        }
        else if (!longdeg.getText().toString().equals("")) {
            longstr = longdeg.getText().toString() + "°" + longmin.getText().toString() + "'" + longsec.getText().toString() + "\"";
            intent.putExtra("longitude",longstr);
        }
        else {
            longstr = "";
        }

        //fix no transfer
        if (!fixnotxt.getText().toString().equals("")) {
            fixnostr = fixnotxt.getText().toString();
            intent.putExtra("fix_no",fixnostr);
        }

        //location id tranfer
        locationidstr = locationidstr.trim();
        if(locationidstr.equals("") || locationidstr.equals("Select a Point name from the List"))
        {
            locationidstr = "";
        }
        intent.putExtra("location_id",locationidstr);

        startActivity(intent);
    }

    //checkboxx observer code
    public void onSelectedob(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        boolean present = false;
        String obs = ((CheckBox) view).getText().toString();
        if (observerstr != null && observerstr.contains(obs))
        {
            present = true;
        }
        switch (view.getId())
        {
            case R.id.user1:
            case R.id.user2:
            case R.id.user3:
                if(checked && !present && observerstr != null)
                {
                    observerstr = observerstr.concat(" "+obs);
                }
                else if (checked && !present && observerstr == null)
                {
                    observerstr = obs;
                }
                else
                {
                    String tempword = obs + " ";
                    observerstr = observerstr.replaceAll(tempword,"");
                    tempword = " " + obs;
                    observerstr = observerstr.replaceAll(tempword,"");
                    tempword = obs;
                    observerstr = observerstr.replaceAll(tempword,"");
                }
                break;
        }
    }

    //edittext observer code
    public String observeredittextstr() {

        obs1 = findViewById(R.id.ob1txt);
        obs2 = findViewById(R.id.ob2txt);
        String result = null;
        if(obs1.getText().toString().equals(""))
        {
            if (!obs2.getText().toString().equals("")) {
                result = obs2.getText().toString();
            }
        }
        else
        {
            if(obs2.getText().toString().equals(""))
            {
                result = obs1.getText().toString();
            }
            else
            {
                if(observerstr.contains(obs1.getText().toString()))
                {
                    result = obs2.getText().toString();
                }
                else if (observerstr.contains(obs2.getText().toString()))
                {
                    result = obs1.getText().toString();
                }
                else
                {
                    result = obs1.getText().toString();
                    result = result.concat(" "+obs2.getText().toString());
                }
            }
        }
        return result;
    }

}
