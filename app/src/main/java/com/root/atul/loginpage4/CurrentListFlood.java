package com.root.atul.loginpage4;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

public class CurrentListFlood extends AppCompatActivity {
    ListView listView;
    TextView date;
    long count=-1;
    DatabaseReference databaseReference,databaseReference1,databaseReference3;
    ArrayAdapter<String> adapter;
    ArrayList state;
    ArrayList<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_list_flood);
        state=new ArrayList();

         date=findViewById(R.id.date);
         databaseReference3=FirebaseDatabase.getInstance().getReference();
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
        adb.setTitle("!!Info UPDATED ACCORDING TO DISASTER").setMessage("Here the effected state \ncome from servers \n so it take \n few seconds\n to load and also need internet\n Connection").setCancelable(false).setIcon(android.R.drawable.star_big_on);

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        adb.create().show();

        //Toast.makeText(this,"you are in flood",Toast.LENGTH_LONG).show();
        listView=(ListView)findViewById(R.id.listview_flood);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);

        listView.setAdapter(adapter);



        databaseReference1= FirebaseDatabase.getInstance().getReference();
        databaseReference1.child("Flood").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                count=dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Flood").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getValue(String.class));
                state.add(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();

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
                Intent intent=new Intent(CurrentListFlood.this,EffectedPeopleList.class);
                intent.putExtra("State",""+state.get(i));
                startActivity(intent);
            }
        });

    }

}
