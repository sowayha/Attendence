package com.example.attendence1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

//save user login i use SharedPreferences
//hide password i use textInput & toggle enabled
//scan QR code

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText e1 =(EditText)findViewById(R.id.email);
        final EditText e2 =(EditText)findViewById(R.id.pass);
        e2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        fAuth = FirebaseAuth.getInstance();

//to keep user login
        SharedPreferences preferences = getSharedPreferences("remember", Context.MODE_PRIVATE);
        if (preferences.contains(String.valueOf(e1))){
            Intent intent = new Intent(MainActivity.this, Mainscreen.class);
            startActivity(intent);
        }



        Button b1 = (Button)findViewById(R.id.enter);
        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                String s2 = e1.getText().toString();
                String s6 = e2.getText().toString();


                if (TextUtils.isEmpty(s2)) {e1.setError("Email is required !");
                    return;}
                if (TextUtils.isEmpty(s6)) {e2.setError("Password is required !");
                    return;}
                if (s6.length() < 6) {e2.setError("Password must be 6 char at least");
                    return;}





                fAuth.signInWithEmailAndPassword(s2, s6).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
//save data
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(String.valueOf(e1), s2);
                    editor.putString(String.valueOf(e2), s6);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Done ^_^", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Mainscreen.class));
                    finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                });

            }
        });





        TextView b2 = (TextView) findViewById(R.id.reg);
        b2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
        }
        });
    }

}