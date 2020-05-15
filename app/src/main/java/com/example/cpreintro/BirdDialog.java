package com.example.cpreintro;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

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
                        Toast.makeText(view.getContext(),"Selected: " +" save " + title +
                                observerstr + datestr + latstr+ longstr+fixnostr+locationidstr, Toast.LENGTH_LONG).show();
                    }
                });



        return builder.create();
    }
}
