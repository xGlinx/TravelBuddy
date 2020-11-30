package com.continental.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.continental.travelbuddy.retrofit.clientComentar;
import com.continental.travelbuddy.retrofit.pruebaClient;
import com.continental.travelbuddy.retrofit.pruebaServicio;
import com.continental.travelbuddy.retrofit.respuesta.RespuestaComentario;
import com.continental.travelbuddy.retrofit.respuesta.RespuestaLogin;
import com.continental.travelbuddy.retrofit.solicitar.SolicitarComentario;
import com.continental.travelbuddy.retrofit.solicitar.SolicitarLogin;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class Comentar extends AppCompatActivity {

    String id, nombre, foto;
    float rating_val= 0;
    String place_id = "0";
    String id_usuario_save = "0";
    Button btn_see_send_comment, btn_send_comment;
    TextView txt_name_place_send_comment;
    ImageView iv_send_comment;
    RatingBar rating_send_comment;
    EditText et_description_send_commment;
    pruebaServicio _pruebaServicio;
    clientComentar _clientComentar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentar);

        btn_see_send_comment = findViewById(R.id.btn_see_send_comment);
        btn_send_comment = findViewById(R.id.btn_send_comment);
        txt_name_place_send_comment = findViewById(R.id.txt_name_place_send_comment);
        iv_send_comment = findViewById(R.id.iv_send_comment);
        rating_send_comment = findViewById(R.id.rating_send_comment);
        et_description_send_commment = findViewById(R.id.et_description_send_commment);

        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        place_id = preferences.getString("place_id_ver","0");
        id_usuario_save = preferences.getString("id_usuario_save","0");

        retrofitInit();

        rating_send_comment.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), "Valor:"+rating, Toast.LENGTH_SHORT).show();
                rating_val = rating;
            }
        });

        list_place();

        btn_send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comentario = et_description_send_commment.getText().toString();
                float rating = rating_val;
                if(comentario.isEmpty()){
                    et_description_send_commment.setError("El comentario es requerido");
                } else if(rating<0 || rating>5){
                    Toast.makeText(Comentar.this, "Debe marcar su calificación", Toast.LENGTH_SHORT).show();
                } else {
                    comentar();
                }
            }
        });

        btn_see_send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPreferencias(place_id);
                Intent i = new Intent(getApplicationContext(), ListaComentarios.class);
                startActivity(i);
            }
        });
    }

    private void retrofitInit() {
        _clientComentar = clientComentar.getInstance();
        _pruebaServicio = _clientComentar.getPruebaServicio();
    }

    private void list_place()
    {
        String url = "http://smartcityhyo.tk/api/Identidad_Cultural/Lista_Lugares_Id.php?id="+place_id;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("place");

                    for (int i = 0 ; i < array.length() ; i++) {
                        JSONObject a = array.getJSONObject(i);
                        id = a.getString("ID_Lugar_Turistico");
                        nombre = a.getString("LT_Nombre");
                        foto = a.getString("FG_URL");

                        txt_name_place_send_comment.setText(nombre);

                        Picasso.get().load(foto)
                                .resize(500,500).placeholder(android.R.drawable.sym_def_app_icon)
                                .error(android.R.drawable.stat_notify_error).into(iv_send_comment);
                    }

                } catch (JSONException e) {
                    Toast.makeText(Comentar.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Comentar.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void comentar(){
        SolicitarComentario solicitarComentario =new SolicitarComentario(place_id, id_usuario_save,et_description_send_commment.getText().toString(), String.valueOf(rating_val));
        Call<RespuestaComentario> call = _pruebaServicio.doComment(solicitarComentario);
        call.enqueue(new Callback<RespuestaComentario>() {
            @Override
            public void onResponse(Call<RespuestaComentario> call, retrofit2.Response<RespuestaComentario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Comentar.this, "COMENTARIO ENVIADO", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Comentar.this, "Datos incorrectos, vuelva a ingresar por favor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaComentario> call, Throwable t) {
                Toast.makeText(Comentar.this, "Problemas de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarPreferencias(String place_id_pass){
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("place_id_ver",place_id_pass);

        editor.commit();
    }
}