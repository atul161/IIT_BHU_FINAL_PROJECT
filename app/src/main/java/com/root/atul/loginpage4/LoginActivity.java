package com.root.atul.loginpage4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends Activity {


    public ConstraintLayout constraintLayout;
    public ConstraintSet cold, cnew;
    EditText et1, et2;
    boolean fix = false;
    Button login;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        constraintLayout = findViewById(R.id.constraintmain);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        pd = new ProgressDialog(this);

        cold = new ConstraintSet();
        cold.clone(constraintLayout);
        cnew = new ConstraintSet();
        cnew.clone(this, R.layout.activity_login_animation);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TransitionManager.beginDelayedTransition(constraintLayout);
                if (!fix) {
                    cnew.applyTo(constraintLayout);
                    fix = true;
                } else {
                    cold.applyTo(constraintLayout);
                    fix = false;
                }

            }
        });

    }

    public void submit(View view) {


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(this, "networkproblem", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("!!OOps").setMessage("Plg check your internet Connection ").setCancelable(false).setIcon(android.R.drawable.star_big_on);

            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            adb.create().show();
        } else {


            login = findViewById(R.id.b1);

            final String contactno, password;
            contactno = et1.getText().toString().trim();
            password = et2.getText().toString().trim();

            if (contactno.isEmpty()) {
                et1.setError("plg fill");
                et1.requestFocus();
            } else {
                if (password.isEmpty()) {
                    et2.setError("plg fill");
                    et2.requestFocus();

                } else {

                    //getting id and password from database
                    pd.setMessage("!!Plg wait we are connecting from Server");
                    pd.show();
                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                    databaseReference1.child("Register").child(contactno).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this, "you have not registered plg register", Toast.LENGTH_LONG).show();
                            } else {
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                                databaseReference1.child("Register").child(contactno).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        String check = dataSnapshot.child("password").getValue(String.class);
                                       // Toast.makeText(LoginActivity.this, "value of check is" + check + password, Toast.LENGTH_SHORT).show();


                                        if (check.equals(password)) {
                                            pd.dismiss();
                                            //go to new Activity
                                            Toast.makeText(LoginActivity.this, "password matched", Toast.LENGTH_LONG).show();
                                           Intent intent = new Intent(LoginActivity.this, AfterLogin.class);
                                           intent.putExtra("Cno",contactno);
                                            startActivity(intent);
                                            //finish();
                                        } else {
                                            pd.dismiss();
                                            Toast.makeText(LoginActivity.this, "password incorrect", Toast.LENGTH_LONG).show();

                                        }


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
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

    public void signup (View view)
    {
       Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);

    }
}
