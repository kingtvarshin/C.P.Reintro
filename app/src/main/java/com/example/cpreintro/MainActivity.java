package com.example.cpreintro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //date
        refreshdate();

        //point name or coordinates
        ToggleButton toggle = (ToggleButton) findViewById(R.id.point_or_coord);
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
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(),"Selected: " +item, Toast.LENGTH_SHORT).show();
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
                    lat.setVisibility(View.VISIBLE);
                    lon.setVisibility(View.VISIBLE);
                    pointname.setVisibility(View.GONE);
                }
                else
                {
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
        TextView date = findViewById(R.id.date);
        date.setText("Date : "+cal.get(DATE)+"/"+cal.get(MONTH)+"/"+cal.get(YEAR));
    }

    //bird dialog onclick
    public void birddialog(View view) {
        BirdDialog birdialog = new BirdDialog();
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
        birdialog.setArguments(bundle);
        birdialog.show(getSupportFragmentManager(),"bird dialog");
    }
}
