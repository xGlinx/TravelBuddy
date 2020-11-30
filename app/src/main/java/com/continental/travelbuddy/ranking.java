package com.continental.travelbuddy;

import android.os.Bundle;
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
import com.continental.travelbuddy.adapter.ListPlacesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ranking extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> dato1,dato2,dato3,dato4;
    String id,nombre,calificacion,foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        list_places_top();
    }

    private void list_places_top()
    {
        recyclerView  = findViewById(R.id.recy_list_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dato1 = new ArrayList<String>();
        dato2 = new ArrayList<String>();
        dato3 = new ArrayList<String>();
        dato4 = new ArrayList<String>();

        String url = "http://smartcityhyo.tk/api/Identidad_Cultural/Lista_Lugares_Top.php";

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

                        if(calificacion=="null")
                        {
                            calificacion = "0";
                        }

                        foto = a.getString("FG_URL");

                        dato1.add(id);
                        dato2.add(nombre);
                        dato3.add(calificacion);
                        dato4.add(foto);
                    }
                    ListPlacesAdapter listPlacesAdapter = new ListPlacesAdapter(dato1, dato2, dato3, dato4,getApplicationContext());
                    recyclerView.setAdapter(listPlacesAdapter);
                } catch (JSONException e) {
                    Toast.makeText(ranking.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ranking.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}