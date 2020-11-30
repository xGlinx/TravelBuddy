package com.continental.travelbuddy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.continental.travelbuddy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPlacesAdapter extends RecyclerView.Adapter<com.continental.travelbuddy.adapter.ListPlacesAdapter.ViewHolderDatos> {
    private ArrayList<String> datos1,datos2,datos3,datos4;
    //ID, NOMBRE, CALIFICACION, FOTO
    Context context;

    public ListPlacesAdapter(ArrayList<String> adatos1, ArrayList<String> adatos2, ArrayList<String> adatos3, ArrayList<String> adatos4, Context context){
        this.datos1 = adatos1;
        this.datos2 = adatos2;
        this.datos3 = adatos3;
        this.datos4 = adatos4;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_places,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.continental.travelbuddy.adapter.ListPlacesAdapter.ViewHolderDatos holder, int position) {
        holder.ponerDatos(datos1.get(position),datos2.get(position),datos3.get(position), datos4.get(position));
    }

    @Override
    public int getItemCount() {
        return datos1.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView list_place_img;
        TextView list_place_name;
        RatingBar list_place_val;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            list_place_name = itemView.findViewById(R.id.list_place_name);
            list_place_val = itemView.findViewById(R.id.list_place_val);
            list_place_img = itemView.findViewById(R.id.list_place_img);

        }
        public void ponerDatos(String a1, String a2, String a3 , String a4){
            //ID, NOMBRE, CALIFICACION, FOTO
            list_place_name.setText(a2);

            list_place_val.setRating(Float.parseFloat(a3));

            Picasso.get().load(a4)
                    .resize(500,500).placeholder(android.R.drawable.sym_def_app_icon)
                    .error(android.R.drawable.stat_notify_error).into(list_place_img);


        }
    }
}
