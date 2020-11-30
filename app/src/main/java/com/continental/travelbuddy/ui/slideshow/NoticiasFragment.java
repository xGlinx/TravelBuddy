package com.continental.travelbuddy.ui.slideshow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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
import com.continental.travelbuddy.Adaptador.EventosAdapter;
import com.continental.travelbuddy.Entidades.Evento;
import com.continental.travelbuddy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoticiasFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

    RecyclerView recyclerEventos;
    ArrayList<Evento> listaEventos;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_noticias, container, false);
        listaEventos=new ArrayList<>();
        recyclerEventos=root.findViewById(R.id.rvEventos);
        recyclerEventos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerEventos.setHasFixedSize(true);
        EventosAdapter adapter=new EventosAdapter(listaEventos);
        recyclerEventos.setAdapter(adapter);

        request= Volley.newRequestQueue(getContext());

        cargarWebService();
        return root;
    }


private void cargarWebService() {
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Conectando");
        progreso.show();
        String url="http://smartcityhyo.tk/api/Evento/List_Evento.php";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        }
@Override
public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se pudo conectar", Toast.LENGTH_SHORT).show();
        Log.i("Error",error.toString());
        progreso.hide();
        }

@Override
public void onResponse(JSONObject response) {
        Evento evento=null;
        JSONArray json=response.optJSONArray("records");
        try{
        for(int i=0;i<json.length();i++){
        evento=new Evento();
        JSONObject jsonObject=null;
        jsonObject=json.getJSONObject(i);
        evento.setEVE_Nombres(jsonObject.optString("EVE_Nombres"));
        evento.setEVE_Descripcion(jsonObject.optString("EVE_Descripcion"));
        evento.setID_Distrito(jsonObject.optInt("ID_Distrito"));
        evento.setDistrito(jsonObject.optString("Distrito"));
        evento.setEVE_Detalles(jsonObject.optString("EVE_Detalles"));
        evento.setEVE_Fotografia(jsonObject.optString("EVE_Fotografia"));
        evento.setEVE_Fecha_Hora(jsonObject.optString("EVE_Fecha_Hora"));
        evento.setEVE_Longitud(jsonObject.optString("EVE_Longitud"));
        evento.setEVE_Latitud(jsonObject.optString("EVE_Latitud"));
        listaEventos.add(evento);

        }
        progreso.hide();
        EventosAdapter adapter=new EventosAdapter(listaEventos);
        recyclerEventos.setAdapter(adapter);

        }catch (JSONException e){
        e.printStackTrace();
        Toast.makeText(getContext(), "No se pudo establecer la conexion con el servidor", Toast.LENGTH_SHORT).show();
        progreso.hide();
        }
        }

}