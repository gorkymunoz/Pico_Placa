package com.gorkymunoz.picoplaca;


import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultarFragment extends Fragment {

    private TextInputLayout layoutMatricula;


    public ConsultarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultar, container, false);
        layoutMatricula = view.findViewById(R.id.layout_placa_ingresada);
        final TextView matriculaIngresada = view.findViewById(R.id.placa_ingresada);
        FloatingActionButton fabHistorial = view.findViewById(R.id.fab_historial);
        Button botonConsultar = view.findViewById(R.id.material_unelevated_button);
        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                consultarPicoPlaca(matriculaIngresada.getText().toString(),calendar);
            }
        });
        fabHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navegarHistorial(v);
            }
        });
        return view;
    }

    private void consultarPicoPlaca(String matricula, Calendar calendar) {
        if(validarMatricula(matricula)){
            return;
        }
        calendar.getTime();
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
        else if(!verificarFormato(matricula)){
            layoutMatricula.setError(getResources().getString(R.string.formato_incorrecto));
            valido = false;
        }
        else {
            layoutMatricula.setError(null);
        }
        return valido;
    }

    private boolean verificarFormato(String matriculaAValidar){
        Pattern patron = Pattern.compile("[a-zA-Z]{2}[a-zA-Z]?\\d{3}\\d?");
        Matcher encaja = patron.matcher(matriculaAValidar);
        return encaja.matches();
    }


}
