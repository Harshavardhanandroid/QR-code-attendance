package com.appdeveloper.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button student,btn3,bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        student=(Button)findViewById(R.id.student);
        btn3=(Button)findViewById(R.id.button3);
        bt4=(Button)findViewById(R.id.button4);
    }
    public void click(View view)
    {
        Intent intent = new Intent(MainActivity.this, Studentlogin.class);
        startActivity(intent);


    }
    public void click1(View view)
    {
        Intent intent = new Intent(MainActivity.this, Main4Activity.class);
        startActivity(intent);

    }
    public void click2(View view)
    {
        Intent intent = new Intent(MainActivity.this, ViewActivity.class);
        startActivity(intent);

    }

}
