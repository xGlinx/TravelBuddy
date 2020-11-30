package com.continental.travelbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.continental.travelbuddy.adapter.ListCommentsAdapter;
import com.continental.travelbuddy.adapter.ListPlacesAllAdaptar;
import com.continental.travelbuddy.adapter.ListProxEventsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class eventosProximos extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> dato1,dato2,dato3,dato4,dato5,dato6,dato7,dato8;
    String id,day_name,day, name, description,detalle,hora,imagen,distrito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_proximos);
        prox_events();
    }

    private void prox_events()
    {
        recyclerView  = findViewById(R.id.recy_list_prox_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dato1 = new ArrayList<String>();
        dato2 = new ArrayList<String>();
        dato3 = new ArrayList<String>();
        dato4 = new ArrayList<String>();
        dato5 = new ArrayList<String>();
        dato6 = new ArrayList<String>();
        dato7 = new ArrayList<String>();
        dato8 = new ArrayList<String>();

        String url = "http://smartcityhyo.tk/api/Identidad_Cultural/list_events_prox.php";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("prox_events");

                    for (int i = 0 ; i < array.length() ; i++) {
                        JSONObject a = array.getJSONObject(i);
                        String dia_nombre_aux = a.getString("dia_nombre");


                        day_name = Dia_nombre(dia_nombre_aux);
                        day = a.getString("dia");
                        name = a.getString("EVE_Nombres");
                        description = a.getString("EVE_Descripcion");
                        detalle = a.getString("EVE_Detalles");
                        hora = a.getString("EVE_Fecha_Hora");
                        imagen = a.getString("EVE_Fotografia");
                        distrito = a.getString("DIS_Nombre");


                        dato1.add(day_name);
                        dato2.add(day);
                        dato3.add(name);
                        dato4.add(description);
                        dato5.add(detalle);
                        dato6.add(hora);
                        dato7.add(imagen);
                        dato8.add(distrito);
                    }
                    ListProxEventsAdapter listProxEventsAdapter = new ListProxEventsAdapter(dato1, dato2, dato3,dato4,dato5,dato6,dato7,dato8,
                            new ListProxEventsAdapter.MyAdapterListener() {
                                @Override
                                public void click(View v, int position) {
                                    String name = dato3.get(position);
                                    String descipcion = dato4.get(position);
                                    String detalle = dato5.get(position);
                                    String hora = dato6.get(position);
                                    String imagen = dato7.get(position);
                                    String distrito = dato8.get(position);


                                    Intent intent = new Intent(eventosProximos.this, DetailEvents.class);
                                    intent.putExtra("name_event", name);
                                    intent.putExtra("description_event", descipcion);
                                    intent.putExtra("detail_event", detalle);
                                    intent.putExtra("time_event", hora);
                                    intent.putExtra("image_event", imagen);
                                    intent.putExtra("district_event", distrito);
                                    startActivity(intent);
                                    //startActivity(new Intent(eventosProximos.this, DetailEvents.class));
                                }

                            }
                    );
                    recyclerView.setAdapter(listProxEventsAdapter);
                } catch (JSONException e) {
                    Toast.makeText(eventosProximos.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(eventosProximos.this, "Lista Vacía.", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public String Dia_nombre(String dia){
        String nombre_del_dia = "";
        switch (dia){
            case "1":
                nombre_del_dia= "Lunes";
                break;
            case "2":
                nombre_del_dia= "Martes";
                break;
            case "3":
                nombre_del_dia= "Miercoles";
                break;
            case "4":
                nombre_del_dia= "Jueves";
                break;
            case "5":
                nombre_del_dia= "Viernes";
                break;
            case "6":
                nombre_del_dia= "Sábado";
                break;
            case "7":
                nombre_del_dia= "Domingo";
                break;
        }

        return nombre_del_dia;
    }
}