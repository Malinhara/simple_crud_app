package com.example.kotlinapp1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Dbhandler extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String Dbname = "Store";
    private static final String TableName = "Items" ;


    //Column
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_DES = "DES";
    private static final String COLUMN_STARTED = "started";
    private static final String COLUMN_FINISHED = "finished";

    public Dbhandler(@Nullable Context context) {
        super(context, Dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableQuery = "CREATE TABLE " + TableName + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DES + " TEXT, " +
                COLUMN_STARTED + " TEXT, " +
                COLUMN_FINISHED + " TEXT)";
        db.execSQL(createTableQuery);
     /*
        CREATE TABLE Items (id INTEGER PRIMARK KEY AUTOINCREMENT,Name TEXT,DES TEXT started TEXT
        ,finshied TEXT);
     */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TableName;
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);//CREATE TABLE AGAIN

    }

    public void insertData(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, model.getName());
        contentValues.put(COLUMN_DES, model.getDeec());
        db.insert(TableName, null, contentValues);
        db.close();
    }

    public List<Model> viewData() {
        List<Model> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TableName;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DES));

                Model model = new Model(id, name, description);
                dataList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataList;
    }


    public void UpdateData(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, model.getName());
        contentValues.put(COLUMN_DES, model.getDeec()); // Add description update
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(model.getId())}; // Use model's ID
        db.update(TableName, contentValues, selection, selectionArgs);
        db.close();
    }
    public void DeleteData(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(model.getId())};
        db.delete(TableName, selection, selectionArgs);
        db.close();
    }







}
