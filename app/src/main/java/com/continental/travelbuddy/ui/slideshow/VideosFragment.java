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
import com.continental.travelbuddy.VideoAdapter;
import com.continental.travelbuddy.YoutubeVideos;
import com.continental.travelbuddy.adapter.UsuariosAdapter;
import com.continental.travelbuddy.adapter.VideosAdapter;
import com.continental.travelbuddy.model.Usuario;
import com.continental.travelbuddy.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;
public class VideosFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    RecyclerView recyclerVideos,recyclerVideo;

    Vector<YoutubeVideos> youtubeVideos=new Vector<YoutubeVideos>();
    ArrayList<Video> listaVideos;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_videos, container, false);
        listaVideos=new ArrayList<>();
        recyclerVideos=(RecyclerView)root.findViewById(R.id.rvVideos);
        recyclerVideo=(RecyclerView)root.findViewById(R.id.rvVideo);
        recyclerVideos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerVideos.setHasFixedSize(true);
        request= Volley.newRequestQueue(getContext());
        cargarWebService();
        cargarVideo();
        return root;
    }
    private void cargarVideo(){

        recyclerVideo.setHasFixedSize(true);
        recyclerVideo.setLayoutManager(new LinearLayoutManager(getContext()));
        youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0GM0D-v8MYw\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CBhgiTimW8M\" frameborder=\"0\" allowfullscreen></iframe>") );

        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);
        recyclerVideo.setAdapter(videoAdapter);
    }
    private void cargarWebService() {
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Conectando");
        progreso.show();
        String url="http://smartcityhyo.tk/api/VideosTuristicos/Listar_Video.php";
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
        Video video=null;
        JSONArray json=response.optJSONArray("records");
        try{
            for(int i=0;i<json.length();i++){
                video=new Video();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                video.setNombre_Lugar_Turistico(jsonObject.optString("Nombre_Lugar_Turistico"));
                video.setVL_Descripcion(jsonObject.optString("VL_Descripcion"));
                video.setVL_URL(jsonObject.optString("VL_URL"));
                listaVideos.add(video);
            }
            progreso.hide();
            VideosAdapter adapter=new VideosAdapter(listaVideos);
            recyclerVideos.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "No se pudo establecer la conexion con el servidor", Toast.LENGTH_SHORT).show();
            progreso.hide();
        }
    }
}