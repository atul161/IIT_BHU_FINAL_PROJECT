package com.root.atul.loginpage4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterPage extends AppCompatActivity {

    EditText name,age,contactno,password,renterPassword;
    Button submit;
    HashMap hm;
    ProgressDialog pd;
    int id[]={R.id.name,R.id.age,R.id.contactno,R.id.password,R.id.renterPassword};
    String string[]=new String[id.length];
    int b=0,count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        contactno=findViewById(R.id.contactno);
        password=findViewById(R.id.password);
        renterPassword=findViewById(R.id.renterPassword);
        pd=new ProgressDialog(this);

        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("!!INfo").setMessage("Your Contact No Wll Be LOGIN ID").setCancelable(false).setIcon(android.R.drawable.star_big_on);

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        adb.create().show();


     }





    public void register(View view)
    {
        int i;
        for (i=0;i<id.length;i++)
        {
            EditText check=findViewById(id[i]);
            String str=check.getText().toString().trim();
            if(str.isEmpty())
            {
                check.setError("plg fill");
                check.requestFocus();
                break;
            }
            else
            {
              string[i]=str;
            }
        }


        if(i==id.length) {
            if (!string[i - 1].equals(string[i - 2])) {
                renterPassword = findViewById(R.id.renterPassword);
                renterPassword.setError("Password Not matched!!");
                renterPassword.requestFocus();

            } else {
                pd.show();
                //insertion into firebase
                //checking internet connection

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo == null) {
                    Toast.makeText(RegisterPage.this, "networkproblem", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder adb = new AlertDialog.Builder(this);
                    adb.setTitle("!!OOps").setMessage("Plg check your internet Connection ").setCancelable(false).setIcon(android.R.drawable.star_big_on);

                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    adb.create().show();
                } else {


                    hm = new HashMap();


                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                    databaseReference1.child("Register").child(string[2]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                if (count == 0) {
                                    Toast.makeText(RegisterPage.this, "Email Available", Toast.LENGTH_LONG).show();
                               pd.dismiss();
                                }
                            } else {

                                DatabaseReference databaserefrence;
                                databaserefrence = FirebaseDatabase.getInstance().getReference();
                                hm.put("Name", string[0]);
                                hm.put("Age", string[1]);
                                hm.put("Contact no", string[2]);
                                hm.put("password", string[3]);
                                databaserefrence.child("Register").child(string[2]).setValue(hm);
                                Toast.makeText(RegisterPage.this, "your Information Stored", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                                count = 1;
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

            }
        }

    }
}
