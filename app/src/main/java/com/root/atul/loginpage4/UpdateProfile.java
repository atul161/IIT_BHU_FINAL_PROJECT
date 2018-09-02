package com.root.atul.loginpage4;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UpdateProfile extends AppCompatActivity {


    ImageView profilepicture,uploadAadharCard;
    public int REQUEST_CODE=1;
    public int REQUEST_CODE1=2;
    Boolean b=false;
    Bitmap bitmap=null,bitmap1=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        profilepicture=findViewById(R.id.profilepicture);
        uploadAadharCard= findViewById(R.id.uploadAadharCard);
    }

    public void uploadImage(View view)
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),REQUEST_CODE);
    }


    public void save(View view)
    {

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
        }
        else {
            Toast.makeText(this, "" + ImportantData.CONTACT_NO, Toast.LENGTH_LONG).show();
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("!!Thankyou").setMessage("Your data stored").setCancelable(false).setIcon(android.R.drawable.star_big_on);

            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            adb.create().show();
        }

        encodeBitmapAndSaveToFirebase();

    }

    public void encodeBitmapAndSaveToFirebase() {
       /* ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50, baos);
        byte [] arr=baos.toByteArray();
        String result=Base64.encodeToString(arr, Base64.DEFAULT);
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        HashMap hashMap=new HashMap();

       /* DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference()
                .child("Register")
                .child(ImportantData.CONTACT_NO);
        hashMap.put("profilepic",result);
        Toast.makeText(this,"child created"+ImportantData.CONTACT_NO,Toast.LENGTH_LONG).show();
        ref.setValue(hashMap);*/


      /*  ByteArrayOutputStream baos1=new  ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr1=baos1.toByteArray();
        String result1=Base64.encodeToString(arr, Base64.DEFAULT);
        DatabaseReference ref1 = FirebaseDatabase.getInstance()
                .getReference()
                .child("Register")
                .child(ImportantData.CONTACT_NO);
        hashMap.put("aadhar",result1);
        ref1.setValue(hashMap);*/
    }

    public void captureImage(View view)
    {

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null && data.getData()!=null)

        {
            Uri uri=data.getData();
            try
            {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                profilepicture.setImageResource(0);
                profilepicture.setImageBitmap(bitmap);
                b=true;

            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }


        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1)
        {
            final Bundle extras = data.getExtras();
            if (extras != null)
            {
                uploadAadharCard = extras.getParcelable("data");
                uploadAadharCard.setImageBitmap(bitmap1);
            }
        }

    }
}
