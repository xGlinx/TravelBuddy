package com.continental.travelbuddy.model;

public class Usuario {
    private Integer id;
    private String especialidad;
    private String fecha;
    private String hora;
    private String usu;
    public String getEspecialidad(){
        return especialidad;
    }
    public void  setEspecialidad(String especialidad){
        this.especialidad=especialidad;
    }
    public String getFecha(){
        return fecha;
    }
    public void  setFecha(String fecha){
        this.fecha=fecha;
    }
    public String getHora(){
        return hora;
    }
    public void  setHora(String hora){
        this.hora =hora;
    }
    public String getUsu(){
        return usu;
    }
    public void  setUsu(String usu){
        this.usu =usu;
    }
    public Integer getId(){
        return id;
    }
    public void  setId(Integer id){
        this.id=id;
    }
}
