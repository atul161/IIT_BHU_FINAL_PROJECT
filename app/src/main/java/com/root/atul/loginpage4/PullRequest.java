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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class PullRequest extends Fragment {
    Button button;
    EditText et1,et2,et3,et4;
    Spinner spinner;
    int spinnerposition=0;
    String items[]={"select Disaster","Flood","Earthquakes","Drought","Tornadoes"};


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_pullrequest, container, false);

        et1=view.findViewById(R.id.currentlocation);
        et2=view.findViewById(R.id.permanentstate);
        et3=view.findViewById(R.id.aadharcardno);
        et4=view.findViewById(R.id.pancardno);
        button=view.findViewById(R.id.pullrequest);
        spinner=(Spinner)view.findViewById(R.id.spin);
        ArrayAdapter aa=new ArrayAdapter(getActivity(),R.layout.spinner_layout,items);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0)
                {
                    spinnerposition=i;
                }
                else
                {
                    spinnerposition=i;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String CurrentLocation,Permanentstate,Aadharcarno,Pancardno;
                CurrentLocation=et1.getText().toString().trim();
                Permanentstate=et2.getText().toString().trim();
                Aadharcarno=et3.getText().toString().trim();
                Pancardno =et4.getText().toString().trim();




                if(CurrentLocation.isEmpty())
                {
                    et1.setError("plg fill");
                    et1.requestFocus();
                }
                else
                {
                    if(Permanentstate.isEmpty())
                    {
                        et2.setError("plg fill");
                        et2.requestFocus();
                    }
                    else
                    {
                        if(Aadharcarno.isEmpty())
                        {
                            et3.setError("plg fill");
                            et3.requestFocus();
                        }
                        else
                        {
                            if(Pancardno.isEmpty())
                            {
                                et4.setError("plg fill");
                                et4.requestFocus();
                            }
                            else if(spinnerposition==0)
                            {
                                Toast.makeText(getActivity(),"plg select Disaster",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
                                adb.setTitle("Plg READ!!!!").setMessage("This is confidental \n records\n one time no \n changebale \nit does not\n depend \n upon no of times\n pull \nif this will wrong\n your id will be\n blocked from server side\nand if you pull \nrequest more and more \nyour data will \noveride no benefit  ").setCancelable(false).setIcon(android.R.drawable.star_big_on);

                                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Register").child(ImportantData.CONTACT_NO);
                                        databaseReference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {



                                              String Name=  dataSnapshot.child("Name").getValue(String.class);
                                                Toast.makeText(getActivity(),"name is"+Name,Toast.LENGTH_LONG).show();


                                                HashMap hashMap=new HashMap();
                                                hashMap.put("AState",Permanentstate.toUpperCase());
                                                hashMap.put("Name",Name);
                                                hashMap.put("AadharCardNo",Aadharcarno);
                                                hashMap.put("Contact No",ImportantData.CONTACT_NO);


                                                if(spinnerposition==1) {
                                                    DatabaseReference databaseReferenceflood=FirebaseDatabase.getInstance().getReference();
                                                    databaseReferenceflood.child(items[spinnerposition].toUpperCase()).child(ImportantData.CONTACT_NO).setValue(hashMap);
                                                }


                                                if(spinnerposition==2) {
                                                    DatabaseReference databaseReferenceearthquakes=FirebaseDatabase.getInstance().getReference();
                                                    databaseReferenceearthquakes.child(items[spinnerposition].toUpperCase()).child(ImportantData.CONTACT_NO).setValue(hashMap);
                                                }

                                                if(spinnerposition==3) {
                                                    DatabaseReference databaseReferencedrought=FirebaseDatabase.getInstance().getReference();
                                                    databaseReferencedrought.child(items[spinnerposition].toUpperCase()).child(ImportantData.CONTACT_NO).setValue(hashMap);
                                                }

                                                if(spinnerposition==4) {
                                                    DatabaseReference databaseReferencetornadoes=FirebaseDatabase.getInstance().getReference();
                                                    databaseReferencetornadoes.child(items[spinnerposition].toUpperCase()).child(ImportantData.CONTACT_NO).setValue(hashMap);
                                                }


                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                });
                                adb.create().show();
                            }
                        }
                    }
                }
            }
        });

        return view;
    }
}
