package com.adi.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        EditText setmasterpass = (EditText) findViewById(R.id.setmasterpass);
        EditText resetmasterpass = (EditText) findViewById(R.id.resetmasterpass);
        Button setpwbtn = (Button) findViewById(R.id.setpwbtn);

        setpwbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setmasterpass.getText().toString().equals(resetmasterpass.getText().toString())){
                    Intent intent = new Intent(SetPassword.this, MainActivity.class);
                    intent.putExtra("key", setmasterpass.getText().toString());
                    startActivity(intent);
                    SetPassword.this.finish();
                }
                else{
                    Toast.makeText(SetPassword.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}