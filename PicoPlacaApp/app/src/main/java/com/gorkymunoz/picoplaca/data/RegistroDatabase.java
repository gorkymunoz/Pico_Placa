package com.gorkymunoz.picoplaca.data;

import android.content.ContentValues;
import android.database.Cursor;

import static com.gorkymunoz.picoplaca.MainActivity.database;

public class RegistroDatabase {


    public static void ingresarRegistro(String matricula, String fechaRegistro, int contravencion){
        ContentValues valoresPorIngresar = new ContentValues();
        valoresPorIngresar.put("matricula",matricula);
        valoresPorIngresar.put("fecha_registro",fechaRegistro);
        valoresPorIngresar.put("contravencion",contravencion);

        database.insert("registro",null,valoresPorIngresar);
    }

    public static Cursor consultarRegistros(){
        return database.rawQuery("SELECT * FROM registro order by id",null);
    }
}
