package com.continental.travelbuddy.ui.slideshow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import com.continental.travelbuddy.adapter.InfoAdapter;
import com.continental.travelbuddy.adapter.UsuariosAdapter;
import com.continental.travelbuddy.model.Info;
import com.continental.travelbuddy.model.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InfoFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{
    RecyclerView recyclerInfo;
    ArrayList<Info> listaInfo;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    Button ahora,cancelar,acptar;
    ImageButton close;
    LinearLayout l1;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_info, container, false);

        listaInfo=new ArrayList<>();
        recyclerInfo=(RecyclerView)root.findViewById(R.id.rvUsuarios);
        recyclerInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerInfo.setHasFixedSize(true);
        request= Volley.newRequestQueue(getContext());
        cargarWebService();
        return  root;
    }
    private void cargarWebService() {
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Conectando");
        progreso.show();
        String url="http://smartcityhyo.tk/api/LugarTuristico/Listar_lugar_turistico.php";
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
        Info info=null;
        JSONArray json=response.optJSONArray("records");
        try{
            for(int i=0;i<json.length();i++){
                info=new Info();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                info.setLT_Nombre(jsonObject.optString("LT_Nombre"));
                info.setLT_Descripcion(jsonObject.optString("LT_Descripcion"));
                info.setLT_URL_Map(jsonObject.optString("LT_URL_Map"));
                info.setLT_Hora_Inicio(jsonObject.optString("LT_Hora_Inicio"));
                info.setLT_Hora_Fin(jsonObject.optString("LT_Hora_Fin"));
                listaInfo.add(info);
            }
            progreso.hide();
            InfoAdapter adapter=new InfoAdapter(listaInfo);
            recyclerInfo.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "No se pudo establecer la conexion con el servidor", Toast.LENGTH_SHORT).show();
            progreso.hide();
        }
    }
}
