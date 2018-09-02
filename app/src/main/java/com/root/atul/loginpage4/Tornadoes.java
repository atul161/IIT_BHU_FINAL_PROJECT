package com.root.atul.loginpage4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tornadoes extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_tornadoes,container,false);

        Button b1=view.findViewById(R.id.floodeffectedAreas);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(getActivity(),CurrentListTornado.class);
                //startActivity(intent);
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setTitle("!!NO Tornado!!").setMessage("No Tornado states in india").setCancelable(false).setIcon(android.R.drawable.star_big_on);

                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                adb.create().show();
            }
        });
        return  view;
    }

}
