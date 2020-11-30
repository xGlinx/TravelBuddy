package com.continental.travelbuddy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.continental.travelbuddy.R;
import com.continental.travelbuddy.model.Usuario;

import java.util.List;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuariosHolder> {
    List<Usuario> listaUsuarios;
    public UsuariosAdapter(List<Usuario> listaUsuarios){
        this.listaUsuarios=listaUsuarios;
    }
    @NonNull
    @Override
    public UsuariosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_usuarios,parent,false);
      RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.WRAP_CONTENT);
      vista.setLayoutParams(layoutParams);
      return new UsuariosHolder(vista);
    }

    @Override
    public  void onBindViewHolder(@NonNull UsuariosHolder holder, int position){
        holder.txt_Espe.setText(listaUsuarios.get(position).getEspecialidad().toString());
        holder.txt_fecha.setText(listaUsuarios.get(position).getFecha().toString());
        holder.txt_hora.setText(listaUsuarios.get(position).getHora().toString());
        holder.txt_usu.setText(listaUsuarios.get(position).getUsu().toString());
    }
    @Override
    public int getItemCount(){
        return listaUsuarios.size();
    }
    public class UsuariosHolder extends RecyclerView.ViewHolder{
   TextView txt_Espe,txt_fecha,txt_hora,txt_usu;
    public UsuariosHolder(View itemView){

        super(itemView);
        txt_Espe=(TextView)itemView.findViewById(R.id.tctNombreLugar);
        txt_fecha=(TextView)itemView.findViewById(R.id.txtDescripción);
        txt_hora=(TextView)itemView.findViewById(R.id.txtUrl);

    }
    }
}
