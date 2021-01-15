package com.example.attendence1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText e1 =(EditText)findViewById(R.id.name);
        final EditText e2 =(EditText)findViewById(R.id.email);
        final EditText e3 =(EditText)findViewById(R.id.add);
        final EditText e4 =(EditText)findViewById(R.id.job);
        final EditText e5 =(EditText)findViewById(R.id.phone);
        final EditText e6 =(EditText)findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();

        Button b1 = (Button)findViewById(R.id.creat);
        //if (mAuth.getCurrentUser() != null) {
         //   startActivity(new Intent(getApplicationContext(), MainActivity.class));
          //  finish();}


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                String s6 = e6.getText().toString();


                if (s1.equals("") && s2.equals("") && s3.equals("") && s4.equals("") && s5.equals("") && s6.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all required fields", Toast.LENGTH_LONG).show();
                return;}
                if (TextUtils.isEmpty(s1)) {e1.setError("Name is required !");
                return;}
                if (TextUtils.isEmpty(s2)) {e2.setError("Email is required !");
                return;}
                if (TextUtils.isEmpty(s3)) {e3.setError("Address is required !");
                return;}
                if (TextUtils.isEmpty(s4)) {e4.setError("Job Title is required !");
                return;}
                if (TextUtils.isEmpty(s5)) {e5.setError("Phone Number is required !");
                return;}
                if (TextUtils.isEmpty(s6)) {e6.setError("Password is required !");
                return;}
                if (s6.length() < 6) {e6.setError("Password must be 6 char at least");
                return;}

//register user in firebase

                mAuth.createUserWithEmailAndPassword(s2, s6).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "user created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else
                        Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                });
            }

        });

        TextView b2 = (TextView) findViewById(R.id.goback);
        b2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });}
}
