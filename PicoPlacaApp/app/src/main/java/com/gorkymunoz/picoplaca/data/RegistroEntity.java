package com.gorkymunoz.picoplaca.data;

import java.util.Date;

public class RegistroEntity {
    private String matricula;
    private String fechaRegistro;
    private int contravencion;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getContravencion() {
        return contravencion;
    }

    public void setContravencion(int contravencion) {
        this.contravencion = contravencion;
    }
}
