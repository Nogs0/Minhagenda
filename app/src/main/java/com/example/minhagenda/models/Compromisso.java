package com.example.minhagenda.models;

import java.time.LocalDate;

public class Compromisso {
    private String hora;
    private String data;
    private String descricao;


    public Compromisso(String data, String hora, String descricao){
        this.hora = hora;
        this.data = data;
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getData() {
        return this.data;
    }
    public void setData(String data){
        this.data = data;
    }

    public String getHora(){
        return this.hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
}
