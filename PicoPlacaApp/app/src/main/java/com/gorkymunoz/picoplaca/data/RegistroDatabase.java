package com.gorkymunoz.picoplaca.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class RegistroDatabase {

    private final Context context;
    public static SQLiteDatabase database;

    public RegistroDatabase(Context context) {
        this.context = context;
    }

    public void insert(String matricula, String fechaRegistro, int contravencion){
        ContentValues valoresPorIngresar = new ContentValues();
        valoresPorIngresar.put("matricula",matricula);
        valoresPorIngresar.put("fecha_registro",fechaRegistro);
        valoresPorIngresar.put("contravencion",contravencion);

        database.insert("registro",null,valoresPorIngresar);
    }
}
