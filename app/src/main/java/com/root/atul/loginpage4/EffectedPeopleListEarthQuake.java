package com.root.atul.loginpage4;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class EffectedPeopleListEarthQuake extends AppCompatActivity {



    ListView listView;
    Map<String,Object> users;
    TextView date,textView;
    String save;
    String state;
    int i=0,z=0;
    Boolean b=false;
    ArrayList al;
    ArrayList alname;
    long count=-1;
    int itemList=-1;
    DatabaseReference databaseReference,databaseReference1,databaseReference3,d1;
    ArrayAdapter<String> adapter;
    ArrayList<String> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effected_people_list_earth_quake);


        textView=findViewById(R.id.textView);
        al=new ArrayList();
        alname=new ArrayList();

        date=findViewById(R.id.date);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        state=bundle.getString("State");
        state=state.toUpperCase();
        textView.setText("Select People To\n" +
                "Donate "+state);


        databaseReference3= FirebaseDatabase.getInstance().getReference();
        databaseReference3.child("Date").child("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                date.setText(dataSnapshot.getValue(String.class).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("!!Info").setMessage("Here the effected people list \ncomes from server \n so it take \n few seconds\n to load and also need internet\n Connection").setCancelable(false).setIcon(android.R.drawable.star_big_on);

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        adb.create().show();

        listView=(ListView)findViewById(R.id.listview_earthquake_people);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);

        listView.setAdapter(adapter);


        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("EARTHQUAKE").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //list.add(dataSnapshot.getValue(String.class));
                //adapter.notifyDataSetChanged();

                int name=0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    i++;
                    String s1=snapshot.getValue(String.class).toString();
                    Toast.makeText(EffectedPeopleListEarthQuake.this,s1,Toast.LENGTH_SHORT).show();
                    if(i%4==1)
                    {
                        save=s1;
                    }

                    if(save.equals(state))
                    {
                        if(i%4==3)
                        {
                            list.add(s1);
                            al.add(s1);
                            adapter.notifyDataSetChanged();
                        }
                        if(i%4==0)
                        {
                            alname.add(s1);
                        }
                    }


                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                list.remove(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        //listening Action from lst view items

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent=new Intent(EffectedPeopleListEarthQuake.this,MessagesSend.class);
                intent.putExtra("Contact No",""+al.get(i));
                intent.putExtra("Name",""+alname.get(i));
                Toast.makeText(EffectedPeopleListEarthQuake.this, ""+"item"+i+"selected", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


    }
}
