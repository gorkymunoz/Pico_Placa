package com.gorkymunoz.picoplaca;


import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gorkymunoz.picoplaca.data.RegistroDatabase;
import com.gorkymunoz.picoplaca.data.RegistroEntity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialFragment extends Fragment {

    private RecyclerView historial;

    public HistorialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_historial, container, false);
        historial = view.findViewById(R.id.rv_historial);
        HistorialAdapter adapter = new HistorialAdapter(obtenerRegistros());
        historial.setAdapter(adapter);
        return view;
    }

    private ArrayList<RegistroEntity> obtenerRegistros(){
        ArrayList<RegistroEntity> listaRegistros = new ArrayList<>();
        Cursor cursor = RegistroDatabase.consultarRegistros();
        do{
            RegistroEntity registro = new RegistroEntity();
            registro.setMatricula(cursor.getString(1));
            registro.setFechaRegistro(cursor.getString(2));
            registro.setContravencion(cursor.getInt(3));
            listaRegistros.add(registro);

        }while (cursor.moveToNext());
        cursor.close();
        return listaRegistros;
    }

}
