package com.continental.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.continental.travelbuddy.adapter.ListCommentsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaComentarios extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> dato1,dato2,dato3,dato4,dato5;
    String foto,comentario,calificacion, nombre, apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comentarios);
        list_comments();
    }

    private void list_comments()
    {
        recyclerView  = findViewById(R.id.recy_list_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dato1 = new ArrayList<String>();
        dato2 = new ArrayList<String>();
        dato3 = new ArrayList<String>();
        dato4 = new ArrayList<String>();
        dato5 = new ArrayList<String>();

        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String place_id_ver = preferences.getString("place_id_ver","0");
        String url = "http://smartcityhyo.tk/api/Identidad_Cultural/Lista_Comentario_Por_Lugar.php?id="+place_id_ver;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("comments");

                    for (int i = 0 ; i < array.length() ; i++) {
                        JSONObject a = array.getJSONObject(i);
                        foto = a.getString("FG_URL");
                        comentario = a.getString("VL_Comentario");
                        calificacion = a.getString("VL_Votos_totales");
                        nombre = a.getString("US_Nombres");
                        apellido = a.getString("US_Apellidos");


                        dato1.add(foto);
                        dato2.add(comentario);
                        dato3.add(calificacion);
                        dato4.add(nombre);
                        dato5.add(apellido);
                    }
                    ListCommentsAdapter listCommentsAdapter = new ListCommentsAdapter(dato1, dato2, dato3,dato4,dato5,getApplicationContext());
                    recyclerView.setAdapter(listCommentsAdapter);
                } catch (JSONException e) {
                    Toast.makeText(ListaComentarios.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListaComentarios.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}