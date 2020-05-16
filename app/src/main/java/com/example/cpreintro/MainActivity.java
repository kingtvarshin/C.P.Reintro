package com.example.cpreintro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.*;

public class MainActivity extends AppCompatActivity {

    EditText datetxt, lattxt, longtxt, fixnotxt, obs1, obs2;

    String observerstr, datestr, latstr, longstr, fixnostr, locationidstr;

    String temp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //date
        refreshdate();

        //point name or coordinates
        final ToggleButton toggle = (ToggleButton) findViewById(R.id.point_or_coord);
        final Spinner pointname = findViewById(R.id.point_name);
        final EditText lat = findViewById(R.id.latitude);
        final EditText lon = findViewById(R.id.longitude);
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
                if (parent.getItemAtPosition(position).equals("Select a Point name from the List")){}
                else
                {
                    locationidstr = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(),"Selected: " +locationidstr, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        lat.setVisibility(View.GONE);
        lon.setVisibility(View.GONE);
        pointname.setVisibility(View.VISIBLE);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    pointname.setSelection(0);
                    lat.setVisibility(View.VISIBLE);
                    lon.setVisibility(View.VISIBLE);
                    pointname.setVisibility(View.GONE);
                }
                else
                {   lat.setText("");
                    lon.setText("");
                    lat.setVisibility(View.GONE);
                    lon.setVisibility(View.GONE);
                    pointname.setVisibility(View.VISIBLE);
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

        BirdDialog birdialog = new BirdDialog();

        //bundle to send values from activity to dialog
        Bundle bundle = new Bundle();
        switch(view.getId())
        {
            case R.id.bird00:
                bundle.putString("title", "bird : 00");
                break;
            case R.id.bird25:
                bundle.putString("title", "bird : 25");
                break;
            case R.id.bird40:
                bundle.putString("title", "bird : 40");
                break;
            case R.id.bird45:
                bundle.putString("title", "bird : 45");
                break;
        }

        // observer transfer
        String editobserver = observeredittextstr();
        if(observerstr == null)
        {
            if(editobserver == null){}
            else
            {
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
                else{}
            }
            else
            {
                if(observerstr.contains(editobserver))
                {}
                else
                {
                    observerstr = observerstr.concat(" "+editobserver);
                }
            }
        }
        temp = editobserver;
        bundle.putString("observer", observerstr);

        //date transfer
        if(datetxt.getText().equals("")){}
        else
        {
            datestr = datetxt.getText().toString();
            bundle.putString("date", datestr);
        }

        //lat transfer
        if(lattxt.getText() == null){}
        else
        {
            latstr = lattxt.getText().toString();
            System.out.println("latstr" + lattxt.getText());
            bundle.putString("lat", latstr);
        }

        //long transfer
        if(longtxt.getText() == null){}
        else
        {
            longstr = longtxt.getText().toString();
            bundle.putString("long", longstr);
        }

        //fix no transfer
        if(fixnotxt.getText() == null){}
        else
        {
            fixnostr = fixnotxt.getText().toString();
            bundle.putString("fixno", fixnostr);
        }

        //location id tranfer
        if(locationidstr == null || locationidstr.equals("Select a Point name from the List"))
        {
            locationidstr = "";
        }
        bundle.putString("locationid", locationidstr);

        birdialog.setArguments(bundle);
        birdialog.show(getSupportFragmentManager(),"bird dialog");
    }

    public void onSelectedob(View view) {

        Boolean checked = ((CheckBox) view).isChecked();
        Boolean present = false;
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

    public String observeredittextstr() {

        obs1 = findViewById(R.id.ob1txt);
        obs2 = findViewById(R.id.ob2txt);
        String result = null;
        if(obs1.getText().toString().equals(""))
        {
            if(obs2.getText().toString().equals(""))
            {}
            else
            {
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
