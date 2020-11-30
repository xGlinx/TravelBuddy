package com.continental.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.continental.travelbuddy.retrofit.pruebaClient;
import com.continental.travelbuddy.retrofit.pruebaServicio;
import com.continental.travelbuddy.retrofit.respuesta.RespuestaRegistro;
import com.continental.travelbuddy.retrofit.solicitar.SolicitudCambiarPass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarContrasenia extends AppCompatActivity {
    EditText edtEmail, edtPass, npass;
    Button btnCambiarPass,btnvolverLogin;

    pruebaClient _pruebaClient;
    pruebaServicio _pruebaServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasenia);
        retrofit();
        findView();
        events();

    }

    private void findView() {
        edtEmail = findViewById(R.id.edtChangeEmail);
        edtPass = findViewById(R.id.edtChangePass);
        npass = findViewById(R.id.pass2);
        btnCambiarPass = findViewById(R.id.btnCambioContra);
        btnvolverLogin= findViewById(R.id.btn_ir_inicioSesion);

    }

    private void retrofit() {
        _pruebaClient = pruebaClient.getInstance();
        _pruebaServicio = _pruebaClient.getPruebaServicio();

    }

    private void events() {
        btnCambiarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarPass();
            }
        });
        btnvolverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguiente();
            }
        });
    }
    public void siguiente() {
        Intent siguiente = new Intent(this,login.class);
        startActivity(siguiente);
    }
    private void cambiarPass() {
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();
        String pass2 = npass.getText().toString();

        if (pass.isEmpty()) {
            edtPass.setError("Contraseña requerida");
        }
            if (pass2.isEmpty()) {
                npass.setError("Repita Contraseña requerida");
            }
                if (pass.length() <= 6) {
                    Toast.makeText(this, "La contraseña debe ser de minimo 6 digitos", Toast.LENGTH_SHORT).show();
                }
                    if (!pass.equals(pass2)) {
                        Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();

                    } else {
                        SolicitudCambiarPass solicitudCambiarPass = new SolicitudCambiarPass(email, pass);//tu constructor espera los parametros espera eso no cambiar pass
                        Call<RespuestaRegistro> call = _pruebaServicio.doChangePass(solicitudCambiarPass);
                        call.enqueue(new Callback<RespuestaRegistro>() {
                            @Override
                            public void onResponse(Call<RespuestaRegistro> call, Response<RespuestaRegistro> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(CambiarContrasenia.this, "Contraseña actualizada correctamente", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(CambiarContrasenia.this, login.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(CambiarContrasenia.this, "Usuario no registrado,vuelva a ingresar email ", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<RespuestaRegistro> call, Throwable t) {
                                Toast.makeText(CambiarContrasenia.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                            }
                        });
        }
    }
}




