package com.example.locomedics;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button login;
TextView skip_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    login=findViewById(R.id.login);
    login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this,Login.class);
        startActivity(intent);
            }
});
         skip_login =findViewById(R.id.skip_login);
         skip_login.setClickable(true);
         skip_login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent myIntent = new Intent(MainActivity.this,Home_screen.class);
                 startActivity(myIntent);
             }
         });


    }
}
