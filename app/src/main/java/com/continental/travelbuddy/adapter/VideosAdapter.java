package com.continental.travelbuddy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.continental.travelbuddy.R;
import com.continental.travelbuddy.model.Video;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosHolder> {
    List<Video> listaVideos;
    TextView url;
    public VideosAdapter(List<Video> listaVideos){
        this.listaVideos=listaVideos;
    }
    @NonNull
    @Override
    public  VideosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_videos,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        url=(TextView)vista.findViewById(R.id.txtUrl);

        return new VideosHolder(vista);
    }



    @Override
    public  void onBindViewHolder(@NonNull VideosHolder holder, int position){
        holder.txt_lugar.setText(listaVideos.get(position).getNombre_Lugar_Turistico().toString());
        holder.txt_desc.setText(listaVideos.get(position).getVL_Descripcion().toString());
        holder.txt_url.setText(listaVideos.get(position).getVL_URL().toString());
        String s = url.getText().toString();
        
    }
    @Override
    public int getItemCount(){
        return listaVideos.size();
    }
    public class VideosHolder extends RecyclerView.ViewHolder{
   TextView txt_lugar,txt_desc,txt_url;
    public VideosHolder(View itemView){

        super(itemView);
        txt_lugar=(TextView)itemView.findViewById(R.id.tctNombreLugar);
        txt_desc=(TextView)itemView.findViewById(R.id.txtDescripción);
        txt_url=(TextView)itemView.findViewById(R.id.txtUrl);

    }
    }


}
