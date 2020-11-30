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
import com.continental.travelbuddy.retrofit.solicitar.SolicitarRegistro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registro extends AppCompatActivity {
    EditText edtNombre, edtEmail, edtContrasena, edtTelefono;
    Button btnRegistro,btnvolverLogin;
    pruebaClient _pruebaClient;
    pruebaServicio _pruebaServicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        retrofitInit();
        findViews();
        events();

    }
    private void retrofitInit() {
        _pruebaClient = pruebaClient.getInstance();
        _pruebaServicio = _pruebaClient.getPruebaServicio();
    }
    private void findViews() {
        edtNombre = findViewById(R.id.etnombre);
        edtTelefono = findViewById(R.id.etcel);
        edtEmail = findViewById(R.id.etemail);
        edtContrasena = findViewById(R.id.etclave);
        btnRegistro = findViewById(R.id.btnRegistrar);
        btnvolverLogin= findViewById(R.id.btn_ir_inicioSesion);
    }


    private void events() {
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                irALogin();
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
    private void irALogin() {
        String name = edtNombre.getText().toString();
        String telefono = edtTelefono.getText().toString();
        String email = edtEmail.getText().toString();
        String apellido = "null";
        String direccion = "null";
        String nacimiento = "null";
        String pass = edtContrasena.getText().toString();
        String userTipo = "Cliente";
        String nacionalidad = "PERU";
        if(name.isEmpty()){
            edtNombre.setError("El nombre es requerido");
        }else if(telefono.isEmpty()) {
            edtTelefono.setError("El telefono es requerido");
        }else if(email.isEmpty()){
            edtEmail.setError("El email es requerido");
        } else if(pass.length()<6){
            edtContrasena.setError("La contraseña debe ser mayor a 6 digitos");
        } else {
            Toast.makeText(this, "valor tamaño:"+pass.length(), Toast.LENGTH_SHORT).show();
            SolicitarRegistro solicitarRegistro =new SolicitarRegistro(name,apellido,direccion,nacimiento,nacionalidad,telefono, email, pass, userTipo);
            Call<RespuestaRegistro> call = _pruebaServicio.doSignUp(solicitarRegistro);
            call.enqueue(new Callback<RespuestaRegistro>() {
                @Override
                public void onResponse(Call<RespuestaRegistro> call, Response<RespuestaRegistro> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(registro.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(registro.this, login.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(registro.this, "Revise sus datos de registro", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaRegistro> call, Throwable t) {
                    Toast.makeText(registro.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
