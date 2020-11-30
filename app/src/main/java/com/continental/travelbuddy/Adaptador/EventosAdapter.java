package com.continental.travelbuddy.Adaptador;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.continental.travelbuddy.Entidades.Evento;
import com.continental.travelbuddy.R;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.EventosHolder>{
    List<Evento> listaEventos;

    public EventosAdapter(List<Evento> listaEventos){
        this.listaEventos=listaEventos;
    }

    @NonNull
    @Override
    public EventosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_eventos,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new EventosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull EventosHolder holder, int position) {
        holder.txtNombre.setText(listaEventos.get(position).getEVE_Nombres().toString());
        holder.txtdescripcion.setText(listaEventos.get(position).getEVE_Descripcion().toString());
        holder.idDistrito.setText(String.valueOf(listaEventos.get(position).getID_Distrito()));
        holder.txtdistrito.setText(listaEventos.get(position).getDistrito().toString().toString());
        holder.txtdetalle.setText(listaEventos.get(position).getEVE_Detalles().toString());
        holder.idfotografia.setImageURI(Uri.parse(listaEventos.get(position).getEVE_Fotografia().toString().toString()));
        holder.txtFechahora.setText(listaEventos.get(position).getEVE_Fecha_Hora().toString().toString());
        holder.txtlongitud.setText(listaEventos.get(position).getEVE_Longitud().toString().toString());
        holder.txtlatitud.setText(listaEventos.get(position).getEVE_Latitud().toString().toString());
        Picasso.get().load(String.valueOf(listaEventos.get(position).getEVE_Fotografia())).resize(500,500).placeholder(android.R.drawable.sym_def_app_icon).error(android.R.drawable.stat_notify_error).into(holder.idfotografia);

    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }

    public class EventosHolder extends RecyclerView.ViewHolder{
        TextView txtNombre;
        TextView txtdescripcion;
        TextView idDistrito;
        TextView txtdistrito;
        TextView txtdetalle;
        ImageView idfotografia;
        TextView txtFechahora;
        TextView txtlongitud;
        TextView txtlatitud;
        public EventosHolder(View itemView){

            super(itemView);
            txtNombre=(TextView)itemView.findViewById(R.id.txtNombre);
            txtdescripcion=(TextView)itemView.findViewById(R.id.txtDescripcion);
            idDistrito=(TextView)itemView.findViewById(R.id.idDistrito);
            txtdistrito=(TextView)itemView.findViewById(R.id.txtdistrito);
            txtdetalle=(TextView)itemView.findViewById(R.id.txtDetalle);
            idfotografia=(ImageView)itemView.findViewById(R.id.idfotografia);
            txtFechahora=(TextView)itemView.findViewById(R.id.txtFechahora);
            txtlongitud=(TextView)itemView.findViewById(R.id.txtlongitud);
            txtlatitud=(TextView)itemView.findViewById(R.id.txtlatitud);

        }
    }
}
