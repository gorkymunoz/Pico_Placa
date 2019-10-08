package com.gorkymunoz.picoplaca.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BasePicoPlaca.db";
    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private String registro = "CREATE TABLE registro(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, matricula TEXT, fecha_registro TEXT, contravencion INTEGER)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(registro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
