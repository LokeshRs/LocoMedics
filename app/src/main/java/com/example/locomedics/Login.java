package com.example.locomedics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Login extends AppCompatActivity {
    Button n;
    EditText email;
    EditText pwd;
    TextView skip_login, registration;
    String emailc;
    String pwdc;
   ProgressDialog pDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        n = (Button)findViewById(R.id.button_login);
        firebaseAuth =FirebaseAuth.getInstance();
        pDialog = new ProgressDialog(this);

        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = (EditText) findViewById(R.id.email_id);
                pwd = (EditText) findViewById(R.id.pass_id);
                emailc = email.getText().toString();
                pwdc= pwd.getText().toString();
                pDialog.setTitle("Validating");
                pDialog.show();
                firebaseAuth.signInWithEmailAndPassword(emailc,pwdc).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            pDialog.dismiss();
                            Toast.makeText(Login.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent myIntent = new Intent(Login.this, Home_screen.class);
                            startActivity(myIntent);
                        }else{
                            pDialog.dismiss();
                            Toast.makeText(Login.this, "Login Failed! Please Enter Correct Credentials", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                    }




    });
        skip_login=findViewById(R.id.skip_login);
        skip_login.setClickable(true);
        skip_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent =new Intent(Login.this,Home_screen.class);
                startActivity(myIntent);

            }
        });
        registration=findViewById(R.id.skip_login);
        registration.setClickable(true);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent =new Intent(Login.this,Registration.class);
                startActivity(myIntent);

            }
        });

    }

}










