package com.root.atul.loginpage4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessagesSend extends AppCompatActivity {
    TextView tvname,tvcontactno;
    DatabaseReference databaseReference1;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_send);
       tvname= findViewById(R.id.tvname);
        tvcontactno=findViewById(R.id.tvcontactno);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
       // Toast.makeText(this,""+bundle.getString("Contact No"),Toast.LENGTH_SHORT).show();
        tvcontactno.setText("Contact No:"+bundle.getString("Contact No"));
        number=bundle.getString("Contact No");
        tvname.setText("Name:"+bundle.getString("Name"));

        databaseReference1= FirebaseDatabase.getInstance().getReference();
        //databaseReference1.child("Flood")

    }

    public void call(View view) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        Uri ref=Uri.parse("tel:"+number);
        intent.setData(ref);
        startActivity(intent);
    }

    public void chat(View view)
    {

    }
}
