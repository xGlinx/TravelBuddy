package com.continental.travelbuddy.model;

public class Video {
    public Integer getID_Video_LT() {
        return ID_Video_LT;
    }

    public void setID_Video_LT(Integer ID_Video_LT) {
        this.ID_Video_LT = ID_Video_LT;
    }

    public Integer getID_Lugar_Turistico() {
        return ID_Lugar_Turistico;
    }

    public void setID_Lugar_Turistico(Integer ID_Lugar_Turistico) {
        this.ID_Lugar_Turistico = ID_Lugar_Turistico;
    }

    public String getNombre_Lugar_Turistico() {
        return Nombre_Lugar_Turistico;
    }

    public void setNombre_Lugar_Turistico(String nombre_Lugar_Turistico) {
        Nombre_Lugar_Turistico = nombre_Lugar_Turistico;
    }

    public String getVL_Descripcion() {
        return VL_Descripcion;
    }

    public void setVL_Descripcion(String VL_Descripcion) {
        this.VL_Descripcion = VL_Descripcion;
    }

    public String getVL_URL() {
        return VL_URL;
    }

    public void setVL_URL(String VL_URL) {
        this.VL_URL = VL_URL;
    }

    private int ID_Video_LT;
    private int ID_Lugar_Turistico;
    private String Nombre_Lugar_Turistico;
    private String VL_Descripcion;
    private String VL_URL;

}
