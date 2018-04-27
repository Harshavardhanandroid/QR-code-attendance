package com.appdeveloper.student;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Main4Activity extends AppCompatActivity {

    Button gen_btn;
    EditText text;
    ImageView image;
    String text2Qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        text=(EditText)findViewById(R.id.text);
        gen_btn=(Button)findViewById(R.id.info);
        image=(ImageView)findViewById(R.id.image);

        gen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View p1) {
                text2Qr=text.getText().toString().trim();
                MultiFormatWriter mfw=new MultiFormatWriter();
                try
                {
                    BitMatrix bm = mfw.encode(text2Qr, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder be = new BarcodeEncoder();
                    Bitmap bmp=be.createBitmap(bm);
                    image.setImageBitmap(bmp);

                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }

        });



      }
}
