package com.gorkymunoz.picoplaca;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gorkymunoz.picoplaca.MainActivity.database;


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
        guardarRegistro(calendar.getTime(),matricula);
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

    private void guardarRegistro(Date fechaActual, String matricula){
        String insert;
        int contravencion = 0;
        if(dentroPicoPlaca(fechaActual)){
            contravencion = 1;
        }
        insert = "INSERT INTO registro(matricula,fecha_registro,contravencion) values("+matricula+","+fechaActual+","+contravencion+")";
        crearDialogo(contravencion);
        database.execSQL(insert);
    }

    private void crearDialogo(int contravencion){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(contravencion==0){
            builder.setMessage("Puede circular con normalidad");
        }else{
            builder.setMessage("No puede circular en este momento");
        }
        builder.setTitle("Resultado de registro")
                .create()
                .show();
    }

    private boolean dentroPicoPlaca(Date horaConsulta){
        Calendar calendar = Calendar.getInstance();
        Date horaInicio, horaFin;
        if (calendar.get(Calendar.HOUR_OF_DAY)<=12){
            calendar.set(Calendar.HOUR_OF_DAY,7);
            calendar.set(Calendar.MINUTE,0);
            horaInicio = calendar.getTime();

            calendar.set(Calendar.HOUR_OF_DAY,9);
            calendar.set(Calendar.MINUTE,30);
            horaFin = calendar.getTime();
        }else{
            calendar.set(Calendar.HOUR_OF_DAY,16);
            calendar.set(Calendar.MINUTE,0);
            horaInicio= calendar.getTime();

            calendar.set(Calendar.HOUR_OF_DAY,19);
            calendar.set(Calendar.MINUTE,30);
            horaFin= calendar.getTime();
        }
        return horaConsulta.before(horaFin) && horaConsulta.after(horaInicio);
    }
}
