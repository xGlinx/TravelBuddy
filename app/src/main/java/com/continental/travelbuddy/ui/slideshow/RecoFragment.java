package com.continental.travelbuddy.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.continental.travelbuddy.R;
import com.continental.travelbuddy.adapter.ListSuggestionsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecoFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<String> dato1,dato2,dato3;
    String foto,comentario,calificacion;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_reco, container, false);
        recyclerView  = root.findViewById(R.id.recy_list_suggestions);

        list_suggestions();
        return root;
    }

    private void list_suggestions()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dato1 = new ArrayList<String>();
        dato2 = new ArrayList<String>();
        dato3 = new ArrayList<String>();

        String url = "http://smartcityhyo.tk/api/Identidad_Cultural/Lista_Sugerencias.php";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("suggestions");

                    for (int i = 0 ; i < array.length() ; i++) {
                        JSONObject a = array.getJSONObject(i);
                        foto = a.getString("FG_URL");
                        comentario = a.getString("LT_Comment");
                        calificacion = a.getString("LT_Ranking");

                        if(comentario == "null")
                        {
                            comentario = "Te aconsejamos visitar este lugar!";
                        }
                        if(calificacion == "null")
                        {
                            calificacion = "0";
                        }

                        dato1.add(foto);
                        dato2.add(comentario);
                        dato3.add(calificacion);
                    }
                    ListSuggestionsAdapter listSuggestionsAdapter = new ListSuggestionsAdapter(dato1, dato2, dato3,getContext());
                    recyclerView.setAdapter(listSuggestionsAdapter);
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

}