package com.continental.travelbuddy.retrofit;

import com.continental.travelbuddy.retrofit.respuesta.RespuestaComentario;
import com.continental.travelbuddy.retrofit.respuesta.RespuestaLogin;
import com.continental.travelbuddy.retrofit.respuesta.RespuestaRegistro;
import com.continental.travelbuddy.retrofit.solicitar.SolicitarComentario;
import com.continental.travelbuddy.retrofit.solicitar.SolicitarLogin;
import com.continental.travelbuddy.retrofit.solicitar.SolicitarRegistro;
import com.continental.travelbuddy.retrofit.solicitar.SolicitudCambiarPass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface pruebaServicio {


    @POST("Usuario/login.php")
    Call<RespuestaLogin> doLogin(@Body SolicitarLogin solicitarLogin);

    @POST("Usuario/Insert_Usuario.php")
    Call<RespuestaRegistro> doSignUp(@Body SolicitarRegistro solicitarRegistro);

    @POST("Usuario/change_password.php")
    Call<RespuestaRegistro> doChangePass(@Body SolicitudCambiarPass solicitudCambiarPass);

    @POST("Insertar_Comentario_Por_Lugar.php")
    Call<RespuestaComentario> doComment(@Body SolicitarComentario solicitarComentario);
}
