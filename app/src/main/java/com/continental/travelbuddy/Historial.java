package com.continental.travelbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.continental.travelbuddy.adapter.ListPlacesAdapter;
import com.continental.travelbuddy.adapter.ListPlacesAllAdaptar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Historial extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> dato1,dato2,dato3,dato4;
    String id,nombre,calificacion,foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        list_places();
    }

    private void list_places()
    {
        recyclerView  = findViewById(R.id.recy_list_places_all);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dato1 = new ArrayList<String>();
        dato2 = new ArrayList<String>();
        dato3 = new ArrayList<String>();
        dato4 = new ArrayList<String>();

        String url = "http://smartcityhyo.tk/api/Identidad_Cultural/Lista_Lugares.php";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("places");

                    for (int i = 0 ; i < array.length() ; i++) {
                        JSONObject a = array.getJSONObject(i);
                        id = a.getString("ID_Lugar_Turistico");
                        nombre = a.getString("LT_Nombre");
                        calificacion = a.getString("LT_Ranking");
                        foto = a.getString("FG_URL");

                        if(calificacion=="null")
                        {
                            calificacion = "0";
                        }

                        dato1.add(id);
                        dato2.add(nombre);
                        dato3.add(calificacion);
                        dato4.add(foto);
                    }
                    ListPlacesAllAdaptar listPlacesAllAdaptar = new ListPlacesAllAdaptar(dato1, dato2, dato3, dato4,getApplicationContext(),
                            new ListPlacesAllAdaptar.MyAdapterListener() {
                                @Override
                                public void comentar(View v, int position) {
                                    String id = dato1.get(position);
                                    guardarPreferencias(id);
                                    Intent i = new Intent(getApplicationContext(), Comentar.class);
                                    startActivity(i);
                                }

                            },
                            new ListPlacesAllAdaptar.MyAdapterListener2() {
                                @Override
                                public void ver_comentarios(View v, int position) {
                                    String id = dato1.get(position);
                                    guardarPreferencias(id);
                                    Intent i = new Intent(getApplicationContext(), ListaComentarios.class);
                                    startActivity(i);
                                }
                            }
                            );
                    recyclerView.setAdapter(listPlacesAllAdaptar);
                } catch (JSONException e) {
                    Toast.makeText(Historial.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Historial.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void guardarPreferencias(String place_id_pass){
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("place_id_ver",place_id_pass);

        editor.commit();
    }
}