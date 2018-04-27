package com.appdeveloper.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

public class ViewActivity extends AppCompatActivity {

    FirebaseDatabase database;

    DatabaseReference ref;
    private ArrayList<String> mUserName = new ArrayList<>();
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("User");
       // tv=(TextView)findViewById(R.id.textView);
        mListView=(ListView)findViewById(R.id.mListView);
        final ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUserName);
        mListView.setAdapter(array);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                mUserName.add(value);
                array.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), "unsuccessful", Toast.LENGTH_LONG).show();

            }
        });

    }
}

