package com.appdeveloper.student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebHistoryItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Studentlogin extends AppCompatActivity
{


     Button reg;
    TextView tv;
    EditText name,email,phone,username,password;
    FirebaseDatabase database;
    DatabaseReference ref;
    User user;



       @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_studentlogin);
            reg=(Button)findViewById(R.id.register);
            tv=(TextView)findViewById(R.id.editTextStudentLogin);
            name=(EditText)findViewById(R.id.editText3);
            email=(EditText)findViewById(R.id.editText4);
            phone=(EditText)findViewById(R.id.editText5);
            username=(EditText)findViewById(R.id.editText6);
            password=(EditText)findViewById(R.id.editText7);
            database=FirebaseDatabase.getInstance();
            ref=database.getReference("User");
            user =new User();

        }


     private void getValues()
     {
         user.setName(name.getText().toString());
         user.setEmail(email.getText().toString());
         user.setPhone(phone.getText().toString());
         user.setUsername(username.getText().toString());
         user.setPassword(password.getText().toString());
     }
     public  void click(View view)
     {
         final String value = username.getText().toString();
         ref.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 getValues();
                 ref.child("User03").setValue(user);
                 Intent intent = new Intent(Studentlogin.this, Main3Activity.class);
                 intent.putExtra("Username",value);
                 startActivity(intent);

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

                 Toast.makeText(getApplicationContext(),"unsuccessful",Toast.LENGTH_LONG).show();

             }
         });
     }



}
