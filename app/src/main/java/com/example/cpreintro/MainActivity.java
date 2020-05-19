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

    EditText datetxt, lattxt, longtxt, fixnotxt, obs1, obs2, latdeg, latmin, latsec, longdeg, longmin, longsec, comment;
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

        //date
        refreshdate();

        //point name or coordinates
        toggle = findViewById(R.id.point_or_coord);
        latlongunit = findViewById(R.id.switchlatlong);
        pointname = findViewById(R.id.point_name);

        //initial visibility
        lattxt.setVisibility(View.GONE);
        longtxt.setVisibility(View.GONE);
        latdeg.setVisibility(View.GONE);
        latmin.setVisibility(View.GONE);
        latsec.setVisibility(View.GONE);
        longdeg.setVisibility(View.GONE);
        longmin.setVisibility(View.GONE);
        longsec.setVisibility(View.GONE);
        latlongunit.setVisibility(View.GONE);
        pointname.setVisibility(View.VISIBLE);

        //point name dropdown
        List<String> pointnamelist = new ArrayList<>();
        pointnamelist.add(0, "Select a Point name from the List");
        pointnamelist.add("near_tent");
        pointnamelist.add("nallah_entrance");
        pointnamelist.add("v_point_old");
        pointnamelist.add("toota_pedh");
        pointnamelist.add("photo");
        pointnamelist.add("span");
        pointnamelist.add("pen_point");
        pointnamelist.add("v_point");
        pointnamelist.add("opposite_point");
        pointnamelist.add("new_span");
        pointnamelist.add("dead_point");
        pointnamelist.add("way_point");
        pointnamelist.add("t40");
        pointnamelist.add("tikri_dhala");
        pointnamelist.add("above_tikri_dhala");
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
                    switch (locationidstr) {
                        case "near_tent":
                            latstr = "31.02346";
                            longstr = "77.28738";
                            break;
                        case "nallah_entrance":
                            latstr = "31.02421";
                            longstr = "77.28677";
                            break;
                        case "v_point_old":
                            latstr = "31.02484";
                            longstr = "77.28751";
                            break;
                        case "toota_pedh":
                            latstr = "31.02485";
                            longstr = "77.29015";
                            break;
                        case "photo":
                            latstr = "31.02286";
                            longstr = "77.28786";
                            break;
                        case "span":
                            latstr = "31.02273";
                            longstr = "77.28879";
                            break;
                        case "pen_point":
                            latstr = "31.02401";
                            longstr = "77.28754";
                            break;
                        case "v_point":
                            latstr = "31.02488";
                            longstr = "77.28746";
                            break;
                        case "opposite_point":
                            latstr = "31.0238";
                            longstr = "77.28805";
                            break;
                        case "new_span":
                            latstr = "31.02271";
                            longstr = "77.28935";
                            break;
                        case "dead_pnt":
                            latstr = "31.0239";
                            longstr = "77.28906";
                            break;
                        case "way_point":
                            latstr = "31.02291";
                            longstr = "77.28951";
                            break;
                        default:
                            latstr = "";
                            longstr = "";
                            break;
                    }

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                {
                    locationidstr = "";
                    latstr = "";
                    longstr = "";
                    pointname.setSelection(0);
                    lattxt.setVisibility(View.VISIBLE);
                    longtxt.setVisibility(View.VISIBLE);
                    latlongunit.setVisibility(View.VISIBLE);
                    latlongunit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(b)
                            {
                                latstr = "";
                                longstr = "";
                                lattxt.setText("");
                                longtxt.setText("");
                                lattxt.setVisibility(View.GONE);
                                longtxt.setVisibility(View.GONE);
                                latdeg.setVisibility(View.VISIBLE);
                                latmin.setVisibility(View.VISIBLE);
                                latsec.setVisibility(View.VISIBLE);
                                longdeg.setVisibility(View.VISIBLE);
                                longmin.setVisibility(View.VISIBLE);
                                longsec.setVisibility(View.VISIBLE);

                            }
                            else
                            {
                                latstr = "";
                                longstr = "";
                                latdeg.setText("");
                                latmin.setText("");
                                latsec.setText("");
                                longdeg.setText("");
                                longmin.setText("");
                                longsec.setText("");
                                lattxt.setVisibility(View.VISIBLE);
                                longtxt.setVisibility(View.VISIBLE);
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
                    latstr = "";
                    longstr = "";
                    latlongunit.setChecked(false);
                    latdeg.setText("");
                    latmin.setText("");
                    latsec.setText("");
                    longdeg.setText("");
                    longmin.setText("");
                    longsec.setText("");
                    lattxt.setText("");
                    longtxt.setText("");
                    lattxt.setVisibility(View.GONE);
                    longtxt.setVisibility(View.GONE);
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

    //date set in field
    public void refreshdate() {
        Calendar cal = getInstance();
        datetxt = findViewById(R.id.datetxt);
        String date = cal.get(DATE)+"/"+cal.get(MONTH)+"/"+cal.get(YEAR);
        datetxt.setText(date);
    }

    //bird dialog onclick
    public void birddialog(View view) {

        //observer string
        {
            String editobserver = observeredittextstr();
            if (observerstr == null) {
                if (editobserver != null) {
                    observerstr = editobserver;
                }
            } else {
                if (editobserver == null) {
                    if (temp != null && observerstr.contains(temp)) {
                        String tempword = temp + " ";
                        observerstr = observerstr.replaceAll(tempword, "");
                        tempword = " " + temp;
                        observerstr = observerstr.replaceAll(tempword, "");
                        tempword = temp;
                        observerstr = observerstr.replaceAll(tempword, "");
                    }
                } else {
                    if (!observerstr.contains(editobserver)) {
                        observerstr = observerstr.concat(" " + editobserver);
                    }
                }
            }
            temp = editobserver;
        }
        //date string
        {
            if (datetxt.getText() != null) {
                datestr = datetxt.getText().toString();
            }
        }
        //comment string
        {
            if (!comment.getText().toString().equals("")) {
                commentstr = comment.getText().toString();
            } else {
                commentstr = "";
            }
        }
        //location id
        {
            locationidstr = locationidstr.trim();
            if (locationidstr.equals("") || locationidstr.equals("Select a Point name from the List")) {
                locationidstr = "";
            }
        }
        //lat
        {
            if (!lattxt.getText().toString().equals("") && locationidstr.equals("")) {
                latstr = lattxt.getText().toString();
            } else if (!latdeg.getText().toString().equals("") && locationidstr.equals("")) {
                latstr = latdeg.getText().toString() + "° 0" + latmin.getText().toString() + "' 0" + latsec.getText().toString() + "\"";
            } else if (locationidstr.equals("")) {
                latstr = "";
            }
        }
        //long
        {
            if (!longtxt.getText().toString().equals("") && locationidstr.equals("")) {
                longstr = longtxt.getText().toString();
            } else if (!longdeg.getText().toString().equals("") && locationidstr.equals("")) {
                longstr = longdeg.getText().toString() + "° 0" + longmin.getText().toString() + "' 0" + longsec.getText().toString() + "\"";
            } else if (locationidstr.equals("")) {
                longstr = "";
            }
        }
        //fix no
        {
            if (!fixnotxt.getText().toString().equals("")) {
                fixnostr = fixnotxt.getText().toString();
            }
        }

        //empty field check
        if(observerstr != null)
            observerstr = observerstr.trim();
        if(datetxt.getText().toString().equals("")
                || fixnotxt.getText().toString().equals("")
                || observerstr.equals(""))
        {
            Toast.makeText(view.getContext(),"please fill the empty fields", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(locationidstr.equals("") && latstr.equals("") && longstr.equals(""))
        {
            Toast.makeText(view.getContext(),"please fill location requirements", Toast.LENGTH_SHORT).show();
            return ;
        }

        Intent intent = new Intent(this, BirdActivity.class);
        intent.putExtra("observer",observerstr);
        intent.putExtra("date",datestr);
        intent.putExtra("comment",commentstr);
        intent.putExtra("latitude", latstr);
        intent.putExtra("longitude", longstr);
        intent.putExtra("fix_no", fixnostr);
        //tag channel string
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
