package com.example.locomedics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    Button register;
    TextView username;
    TextView password;
    FirebaseAuth firebaseAuth;
    ProgressDialog progress;
    String uname, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        register = findViewById(R.id.register);
        username=findViewById(R.id.r_username);
        password=findViewById(R.id.r_password);

        firebaseAuth =FirebaseAuth.getInstance();
        progress = new ProgressDialog(this);
        progress.setTitle("Registering New user");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.show();
                uname=username.getText().toString();
                pwd=password.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(uname,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          progress.dismiss();
                          Toast.makeText(Registration.this,"Registration Succesfull",Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(Registration.this,Login.class));
                      }
                    }
                });
            }
        });
    }
}
