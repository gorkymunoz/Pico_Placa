package com.gorkymunoz.picoplaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.gorkymunoz.picoplaca.data.DbOpenHelper;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obtenerBase();
    }

    private void obtenerBase(){
        DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
        database = dbOpenHelper.getWritableDatabase();
    }
}
