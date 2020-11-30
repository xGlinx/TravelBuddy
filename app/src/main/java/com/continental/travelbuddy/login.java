package com.continental.travelbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.continental.travelbuddy.retrofit.pruebaClient;
import com.continental.travelbuddy.retrofit.pruebaServicio;
import com.continental.travelbuddy.retrofit.respuesta.RespuestaLogin;
import com.continental.travelbuddy.retrofit.solicitar.SolicitarLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    EditText edtEmail, edtPass;
    Button btnLogin, btnIrRegistro;
    TextView btnIrCambiarPass;
    pruebaClient _pruebaClient;
    pruebaServicio _pruebaServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        retrofitInit();
        findViews();
        events();
    }

        private void retrofitInit() {
            _pruebaClient = pruebaClient.getInstance();
            _pruebaServicio = _pruebaClient.getPruebaServicio();
        }
        private void findViews() {
            edtEmail = findViewById(R.id.etUsuario);
            edtPass = findViewById(R.id.etPass);
            btnLogin = findViewById(R.id.btnIniciar);
            btnIrRegistro = findViewById(R.id.btn_registrarLogin);
            btnIrCambiarPass= findViewById(R.id.txt_cambiarPass);
        }

        private void events() {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    irALogin();
                }
            });



            btnIrRegistro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    irARegistro();
                }
            });
            btnIrCambiarPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(login.this, CambiarContrasenia.class);
                    startActivity(i);
                    finish();

                }
            });

        }


        private void irARegistro() {
            Intent i = new Intent(login.this, registro.class);
            startActivity(i);
            finish();
        }

        private void irALogin() {
            String email = edtEmail.getText().toString();
            String pass = edtPass.getText().toString();
            if(email.isEmpty()){
                edtEmail.setError("El email es requerido");
            } else if(pass.isEmpty()){
                edtPass.setError("Contraseña requerida");
            } else {
                SolicitarLogin solicitarLogin =new SolicitarLogin(email, pass);
                Call<RespuestaLogin> call = _pruebaServicio.doLogin(solicitarLogin);
                call.enqueue(new Callback<RespuestaLogin>() {
                    @Override
                    public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                        if(response.isSuccessful()){
                            guardarPreferencias("140");
                            Toast.makeText(login.this, "Sesion iniciada correctamente", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(login.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(login.this, "Datos incorrectos, vuelva a ingresar por favor", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RespuestaLogin> call, Throwable t) {
                        Toast.makeText(login.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        private void guardarPreferencias(String id_usuario_save){
            SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("id_usuario_save",id_usuario_save);

            editor.commit();
        }
    }