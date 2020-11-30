package com.continental.travelbuddy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.continental.travelbuddy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListProxEventsAdapter extends RecyclerView.Adapter<ListProxEventsAdapter.ViewHolderDatos> {
    private ArrayList<String> datos1,datos2,datos3,datos4,datos5,datos6,datos7,datos8;

    public ListProxEventsAdapter(ArrayList<String> adatos1, ArrayList<String> adatos2, ArrayList<String> adatos3,ArrayList<String> adatos4,ArrayList<String> adatos5,ArrayList<String> adatos6,ArrayList<String> adatos7, ArrayList<String> adatos8, MyAdapterListener listener){
        this.datos1 = adatos1;
        this.datos2 = adatos2;
        this.datos3 = adatos3;
        this.datos4 = adatos4;
        this.datos5 = adatos5;
        this.datos6 = adatos6;
        this.datos7 = adatos7;
        this.datos8 = adatos8;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_prox_events,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.ponerDatos(datos1.get(position),datos2.get(position),datos3.get(position),datos4.get(position),datos5.get(position),datos6.get(position),datos7.get(position));
    }

    @Override
    public int getItemCount() {
        return datos1.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView list_prox_event_dia_name;
        TextView list_prox_event_dia;
        Button list_prox_event_btn;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            list_prox_event_dia_name = itemView.findViewById(R.id.list_prox_event_dia_name);
            list_prox_event_dia = itemView.findViewById(R.id.list_prox_event_dia);

            list_prox_event_btn =itemView.findViewById(R.id.list_prox_event_btn);

            list_prox_event_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.click(v,getAdapterPosition());
                }
            });

        }
        public void ponerDatos(String a1, String a2, String a3,String a4,String a5,String a6,String a7){
            list_prox_event_dia_name.setText(a1);
            list_prox_event_dia.setText(a2);
            list_prox_event_btn.setText(a3);
        }
    }
    public MyAdapterListener onClickListener;
    public interface MyAdapterListener {

        void click(View v, int position);
    }

}
