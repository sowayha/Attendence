package com.example.attendence1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Mainscreen extends AppCompatActivity {
    public static final String e2 = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

//save data
        SharedPreferences preferences = getSharedPreferences("rem", Context.MODE_PRIVATE);
        if (preferences.contains(e2)){
            Toast.makeText(getApplicationContext(),"Hello" + preferences.getString(e2, ""), Toast.LENGTH_LONG).show();
        }

//        ImageView cam = findViewById(R.id.open);
//        cam.setOnClickListener(view -> {
//            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//            startActivity(intent);
//        });

        ImageView cam = findViewById(R.id.open);
        //cam.setOnClickListener(view -> {
        //    Intent intent = new Intent(this, FaceId.class);
        //    startActivity(intent);
        // });

    }

    

}