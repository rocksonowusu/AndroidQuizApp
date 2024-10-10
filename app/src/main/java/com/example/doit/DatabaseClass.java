package com.example.doit;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseClass extends SQLiteOpenHelper {

    public DatabaseClass(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, email TEXT, password TEXT)");
        db.execSQL("CREATE TABLE userProfile (id INTEGER PRIMARY KEY AUTOINCREMENT, userId INTEGER, profile_pic BLOB, fullname TEXT, address TEXT, country TEXT, telephone TEXT, FOREIGN KEY(userId) REFERENCES users(id))");
        db.execSQL("CREATE TABLE scores (id INTEGER PRIMARY KEY AUTOINCREMENT, userId INTEGER, score INTEGER, category TEXT, FOREIGN KEY(userId) REFERENCES users(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS userProfile");
        db.execSQL("DROP TABLE IF EXISTS scores");
        onCreate(db);
    }

    public boolean insertCredentials(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = db.insert("users", null, contentValues);
        return result != -1;
    }

    public String getEmailByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email FROM users WHERE username = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
            cursor.close();
            return email;
        }
        cursor.close();
        return null;
    }

    public void insertDefaultProfile(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", userId);
        contentValues.put("profile_pic", (byte[]) null); // or some default value
        contentValues.put("fullname", ""); // or some default value
        contentValues.put("address", ""); // or some default value
        contentValues.put("country", ""); // or some default value
        contentValues.put("telephone", ""); // or some default value

        db.insert("userProfile", null, contentValues);
    }

    public boolean updateUserProfile(int userId, byte[] profilePic, String fullname, String address, String country, String telephone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (profilePic != null) contentValues.put("profile_pic", profilePic);
        if (fullname != null) contentValues.put("fullname", fullname);
        if (address != null) contentValues.put("address", address);
        if (country != null) contentValues.put("country", country);
        if (telephone != null) contentValues.put("telephone", telephone);

        int result = db.update("userProfile", contentValues, "userId = ?", new String[]{String.valueOf(userId)});
        db.close(); // Close the database after the update operation
        return result > 0;
    }



    public Cursor getUserProfile(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM userProfile WHERE userId = ?", new String[]{String.valueOf(userId)});
    }

    public int getUserIdByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM users WHERE email = ?", new String[]{email});
        if (cursor != null && cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            cursor.close();
            return userId;
        }
        return -1;
    }

    @SuppressLint("Range")
    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"id"}, "username=? AND password=?", new String[]{username, password}, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public void addScore(int userId, int score, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", userId);
        contentValues.put("score", score);
        contentValues.put("category", category); // Add category here
        db.insert("scores", null, contentValues);
        db.close();
    }


    public int getUserIdByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM users WHERE username = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            cursor.close();
            return userId;
        } else {
            cursor.close();
            return -1; // Indicate that user ID was not found
        }
    }

    // Method to get username by user ID
    public String getUsernameById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT username FROM users WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            String username = cursor.getString(0);
            cursor.close();
            return username;
        } else {
            cursor.close();
            return null; // Indicate that username was not found
        }
    }

    public int getHighScoreForCategory(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MAX(score) FROM scores WHERE category = ?";
        Cursor cursor = db.rawQuery(query, new String[]{category});
        int highScore = 0;
        if (cursor.moveToFirst()) {
            highScore = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return highScore;
    }

    public void updateHighScore(String category, int newScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        int currentHighScore = getHighScoreForCategory(category);
        if (newScore > currentHighScore) {
            ContentValues values = new ContentValues();
            values.put("category", category);
            values.put("score", newScore);
            db.insert("scores", null, values);
        }
        db.close();
    }

    @SuppressLint("Range")
    public Bitmap getProfilePictureById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("userProfile", new String[]{"profile_pic"}, "userId=?", new String[]{String.valueOf(userId)}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                byte[] blob = cursor.getBlob(cursor.getColumnIndex("profile_pic"));
                cursor.close();
                return BitmapFactory.decodeByteArray(blob, 0, blob.length);
            }
            cursor.close();
        }
        return null;
    }







}

