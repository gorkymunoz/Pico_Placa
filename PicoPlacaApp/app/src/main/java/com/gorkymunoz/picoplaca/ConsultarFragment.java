package com.gorkymunoz.picoplaca;


import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultarFragment extends Fragment {

    TextInputLayout layoutMatricula;


    public ConsultarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultar, container, false);
        layoutMatricula = view.findViewById(R.id.layout_placa_ingresada);
        FloatingActionButton fabHistorial = view.findViewById(R.id.fab_historial);
        fabHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navegarHistorial(v);
            }
        });
        return view;
    }

    private void navegarHistorial(View view){
        Navigation.findNavController(view).navigate(R.id.action_consultarFragment_to_historialFragment);
    }

    private Boolean validarMatricula(String matricula){
        boolean valido = true;
        if(TextUtils.isEmpty(matricula)){
            layoutMatricula.setError(getResources().getString(R.string.obligatorio));
            valido = false;
        }
        return valido;
    }

}
