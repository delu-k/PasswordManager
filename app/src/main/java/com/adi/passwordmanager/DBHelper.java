package com.adi.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "passwords.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE PASSWORD_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, WEBSITE TEXT, USERNAME TEXT, PASSWORD TEXT);";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertOne(ManagerModel managerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("WEBSITE", managerModel.getWebsite());
        cv.put("USERNAME", managerModel.getUsername());
        cv.put("PASSWORD", managerModel.getPassword());

        long insert = db.insert("PASSWORD_TABLE", null, cv);
        db.close();
        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public int getLastRowNum() {
        SQLiteDatabase db = this.getReadableDatabase();
        long c = DatabaseUtils.queryNumEntries(db, "PASSWORD_TABLE");
        int count = (int) c;
        db.close();
        return count;
    }

    public List<ManagerModel> getEveryone() {
        List<ManagerModel> returnList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PASSWORD_TABLE", null);
        if(cursor.moveToFirst()) {
            do {
                String website = cursor.getString(1);
                String username = cursor.getString(2);
                String password = cursor.getString(3);

                ManagerModel newEntry = new ManagerModel(website, username, password);
                returnList.add(newEntry);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public String getWeb() {
        String website = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PASSWORD_TABLE", null);
        cursor.moveToPosition(getLastRowNum());
        website = cursor.getString(1);
//        if(cursor.moveToFirst()) {
//            do {
//                website = cursor.getString(1);
//            }while(cursor.moveToNext());
//        }
        cursor.close();
        db.close();
        return website;
    }

    public String getUser() {
        String username = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PASSWORD_TABLE", null);
        cursor.moveToPosition(getLastRowNum());
        username = cursor.getString(2);
//        if(cursor.moveToFirst()) {
//            do {
//                username = cursor.getString(2);
//            }while(cursor.moveToNext());
//        }
        cursor.close();
        db.close();
        return username;
    }
}
