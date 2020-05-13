package com.example.cpreintro;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class BirdDialog extends AppCompatDialogFragment {

    String title;
    private EditText time;
    private EditText azimuthbe;
    private RadioButton ws;
    private RadioButton ss;
    private RadioButton nd;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.bird_dialog,null);

        if (getArguments() != null) {
            title = getArguments().getString("title","");
        }

        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        time = view.findViewById(R.id.edittime);
        azimuthbe = view.findViewById(R.id.editazbe);
        ws = view.findViewById(R.id.radiows);
        ss = view.findViewById(R.id.radioss);
        nd = view.findViewById(R.id.radiond);

        return builder.create();
    }
}
