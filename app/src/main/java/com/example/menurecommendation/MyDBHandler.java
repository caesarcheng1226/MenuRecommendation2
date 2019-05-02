package com.example.menurecommendation;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Recipe.db";
    public static final String TABLE_NAME = "Cuisine";
    public static final String UNIQUE_ID = "CuisineID";
    public static final String CUISINE_NAME = "CuisineName";
    public static final String IMAGE_NAME = "ImageName";
    //initialize the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UNIQUE_ID +
                "INTEGER PRIMARYKEY," + CUISINE_NAME + "TEXT," + IMAGE_NAME +"TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    public String loadHandler() {
        String result = "";
        String query = "Select*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            int result_1 = cursor.getInt(1);
            String result_2 = cursor.getString(2);
            result += String.valueOf(result_0) + " " + result_1 + " " + result_2 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(CuisineData Cuisine) {
        ContentValues values = new ContentValues();
        values.put(UNIQUE_ID, Cuisine.getCuisineID());
        values.put(CUISINE_NAME, Cuisine.getCuisineName());
        values.put(IMAGE_NAME, Cuisine.getCuisineImage());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public CuisineData findHandler(int NOP) {
        String query = "Select * FROM " + TABLE_NAME + "WHERE" + CUISINE_NAME + " = /'" + NOP + "/'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CuisineData cuisineData = new CuisineData();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            cuisineData.setCuisineID(Integer.parseInt(cursor.getString(0)));
            cuisineData.setCuisineName(cursor.getString(1));
            cuisineData.setCuisineImage(cursor.getString(2));
            cursor.close();
        } else {
            cuisineData = null;
        }
        db.close();
        return cuisineData;
    }

    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select*FROM" + TABLE_NAME + "WHERE" + UNIQUE_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CuisineData cuisineData = new CuisineData();
        if (cursor.moveToFirst()) {
            cuisineData.setCuisineID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, UNIQUE_ID + "=?",
                    new String[] {
                String.valueOf(cuisineData.getCuisineID())
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(int ID, int NOP, String District) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(UNIQUE_ID, ID);
        args.put(CUISINE_NAME, NOP);
        args.put(IMAGE_NAME, District);
        return db.update(TABLE_NAME, args, UNIQUE_ID + "=" + ID, null) > 0;
    }
}
