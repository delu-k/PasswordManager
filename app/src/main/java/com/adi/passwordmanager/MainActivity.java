package com.adi.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // checking for first launch to set password
        Boolean isFirstRun = getSharedPreferences("PREFERENCE13", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if(isFirstRun){
            getSharedPreferences("PREFERENCE13", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
            startActivity(new Intent(MainActivity.this, SetPassword.class));
            MainActivity.this.finish();
        }

        // get password from SetPassword
        Intent intent = getIntent();

        // main activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText masterpass = (EditText) findViewById(R.id.masterpass);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean temp = getSharedPreferences("PASSB1", MODE_PRIVATE).getBoolean("temp", true);
                if(temp){
                    getSharedPreferences("PASSB1", MODE_PRIVATE).edit().putBoolean("temp", false).commit();
                    getSharedPreferences("PASSWORD4", MODE_PRIVATE).edit().putString("pw", intent.getStringExtra("key")).commit();
                }
                String passw = getSharedPreferences("PASSWORD4", MODE_PRIVATE).getString("pw", "admin");
                if(masterpass.getText().toString().equals(passw)){
                    //viewList();
                    startActivity(new Intent(MainActivity.this, PasswordList.class));
                    MainActivity.this.finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}