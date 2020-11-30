
package com.continental.travelbuddy.retrofit.solicitar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolicitudCambiarPass {

    @SerializedName("US_Email")
    @Expose
    private String USEmail;
    @SerializedName("US_Contrasena")
    @Expose
    private String uSContrasena;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SolicitudCambiarPass() {
    }

    /**
     * 
     * @param uSContrasena
     * @param USEmail
     */
    public SolicitudCambiarPass(String USEmail, String uSContrasena) {
        super();
        this.USEmail = USEmail;
        this.uSContrasena = uSContrasena;
    }

    public String getUSEmail() {
        return USEmail;
    }

    public void setUSEmail(Integer iDUsuario) {
        this.USEmail = USEmail;
    }

    public String getUSContrasena() {
        return uSContrasena;
    }

    public void setUSContrasena(String uSContrasena) {
        this.uSContrasena = uSContrasena;
    }

}
