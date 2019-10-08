package com.gorkymunoz.picoplaca;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gorkymunoz.picoplaca.data.RegistroEntity;

import java.util.ArrayList;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder> {

    private ArrayList<RegistroEntity> listaRegistros;

    public HistorialAdapter(ArrayList<RegistroEntity> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    @NonNull
    @Override
    public HistorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new HistorialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialViewHolder holder, int position) {

        RegistroEntity registroActual = listaRegistros.get(position);
        /*String matriculaMostrar = Resources.getSystem()
                .getString(
                        R.string.matricula,
                        registroActual.getMatricula());*/
        String matriculaMostrar = "Matricula: "+registroActual.getMatricula();
        holder.matricula.setText(matriculaMostrar);
        holder.fechaRegistro.setText(registroActual.getFechaRegistro());
        String contravencion;
        if (registroActual.getContravencion() == 0) {
            contravencion = "Contravención: No";
            //contravencion = Resources.getSystem().getString(R.string.estado_contravencion, "No");
        } else {
            contravencion = "Contravención: Si";
            //contravencion = Resources.getSystem().getString(R.string.estado_contravencion, "Si");
        }
        holder.contravencion.setText(contravencion);
    }

    @Override
    public int getItemCount() {
        return listaRegistros.size();
    }

    class HistorialViewHolder extends RecyclerView.ViewHolder {

        TextView matricula, fechaRegistro, contravencion;

        HistorialViewHolder(@NonNull View itemView) {
            super(itemView);
            matricula = itemView.findViewById(R.id.matricula);
            fechaRegistro = itemView.findViewById(R.id.fecha_registro);
            contravencion = itemView.findViewById(R.id.contravencion);
        }

        public void formatoFecha(String fechaRegistro){

        }
    }

}


