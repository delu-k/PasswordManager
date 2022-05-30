package com.adi.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GetInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_input);

        EditText webInput = (EditText) findViewById(R.id.webName_input);
        EditText userInput = (EditText) findViewById(R.id.userName_input);
        EditText passInput = (EditText) findViewById(R.id.password_input);
        Button addToDB = (Button) findViewById(R.id.addToDB);

        addToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagerModel managerModel;

                String website = webInput.getText().toString();
                String username = userInput.getText().toString();
                String password = passInput.getText().toString();

                managerModel = new ManagerModel(website, username, password);

                DBHelper dbHelper = new DBHelper(GetInput.this);
                boolean success = dbHelper.insertOne(managerModel);

                if(success) {
                    Toast.makeText(GetInput.this, "Success!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(GetInput.this, "Entry failed", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(GetInput.this, PasswordList.class);
                intent.putExtra("key", true);
                startActivity(intent);
                GetInput.this.finish();
            }
        });
    }
}