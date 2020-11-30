package com.continental.travelbuddy.retrofit.solicitar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitarComentario {
    @SerializedName("ID_Lugar_Turistico")
    @Expose
    private String id_Lugar;
    @SerializedName("ID_Usuario")
    @Expose
    private String id_Usuario;
    @SerializedName("VL_Comentario")
    @Expose
    private String vl_comentario;
    @SerializedName("VL_Votos_totales")
    @Expose
    private String vl_votos;

    /**
     * No args constructor for use in serialization
     *
     */
    public SolicitarComentario() {
    }

    /**
     *
     * @param id_Lugar
     * @param id_Usuario
     * @param vl_comentario
     * @param vl_votos
     */
    public SolicitarComentario(String id_Lugar, String id_Usuario, String vl_comentario, String vl_votos) {
        super();
        this.id_Lugar = id_Lugar;
        this.id_Usuario = id_Usuario;
        this.vl_comentario = vl_comentario;
        this.vl_votos = vl_votos;
    }

    public String getId_Lugar() {
        return id_Lugar;
    }

    public void setId_Lugar(String id_Lugar) {
        this.id_Lugar = id_Lugar;
    }

    public String getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(String id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getVl_comentario() {
        return vl_comentario;
    }

    public void setVl_comentario(String vl_comentario) {
        this.vl_comentario = vl_comentario;
    }

    public String getVl_votos() {
        return vl_votos;
    }

    public void setVl_votos(String vl_votos) {
        this.vl_votos = vl_votos;
    }
}
