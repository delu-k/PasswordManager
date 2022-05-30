package com.adi.passwordmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class PasswordList extends AppCompatActivity {
    private ArrayList<PasswordListItem> pwList;
    private RecyclerView pRecyclerView;
    private PasswordListAdapter pAdapter;
    private RecyclerView.LayoutManager pLayoutManager;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_list);

        createExampleList();
        buildRecyclerView();

        Button addEntry = (Button) findViewById(R.id.addEntryBtn);

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PasswordList.this, GetInput.class));
                PasswordList.this.finish();
            }
        });
        Intent intent = getIntent();
        Boolean temp = intent.getBooleanExtra("key", false);
        if(temp) {
            insertItem(0);
        }

    }

    public void insertItem(int pos) {
        pwList.add(pos, new PasswordListItem("temp", "temp"));
        pAdapter.notifyItemInserted(pos);
    }

    public void deleteItem(int pos) {
        pwList.remove(pos);
        pAdapter.notifyItemRemoved(pos);
    }

    public void changeItem(int pos, String text) {
        pwList.get(pos).changeText2(text);
        pAdapter.notifyItemChanged(pos);
    }

    public void createExampleList() {
        pwList = new ArrayList<>();
        pwList.add(new PasswordListItem("Website goes here!", "Username goes here!"));
        pwList.add(new PasswordListItem("G-Mail", "adityarenukdas@gmail.com"));
        pwList.add(new PasswordListItem("College mail", "1032200786@mitwpu.edu.in"));
        pwList.add(new PasswordListItem("Discord", "Aditya"));
//        List<ManagerModel> = dbHelper.getEveryone();

    }

    public void buildRecyclerView() {
        pRecyclerView = findViewById(R.id.recyclerView);
        pRecyclerView.setHasFixedSize(true);
        pLayoutManager = new LinearLayoutManager(this);
        pAdapter = new PasswordListAdapter(pwList);

        pRecyclerView.setLayoutManager(pLayoutManager);
        pRecyclerView.setAdapter(pAdapter);

        pAdapter.setOnItemClickListener(new PasswordListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                changeItem(pos, "Password here!");
            }

            @Override
            public void onDeleteClick(int pos) {
                delConfirm(pos);
            }
        });
    }

    public void delConfirm(int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PasswordList.this);
        builder.setCancelable(true);
        builder.setTitle("Delete entry");
        builder.setMessage("Do you want to delete this entry?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteItem(pos);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}