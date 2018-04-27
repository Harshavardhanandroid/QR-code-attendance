package com.appdeveloper.student;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Main3Activity extends AppCompatActivity {

    String Username;
    FirebaseDatabase database;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Username=getIntent().getExtras().getString("Username");
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("User");
        final Activity activity=this;
        IntentIntegrator integrator=new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data)
    {
        final String value = Username.toString();
        final IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
       final String answer=value.concat(String.valueOf(result));

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ref.child("User04").setValue(""+answer);
                if(result!=null)
                {
                    if(result.getContents()==null)
                    {
                        Toast.makeText(getApplicationContext(),"you cancelled",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        AlertDialog.Builder ale=new AlertDialog.Builder(Main3Activity.this);
                        ale.setCancelable(true);
                        ale.setTitle("welcome");
                        ale.setMessage("Scaned succesfully and your prensent for the class");
                        Toast.makeText(getApplicationContext(),""+Username,Toast.LENGTH_LONG).show();
                        ale.show();
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_LONG).show();

            }
        });
    }
}
