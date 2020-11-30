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

public class ListSuggestionsAdapter extends RecyclerView.Adapter<com.continental.travelbuddy.adapter.ListSuggestionsAdapter.ViewHolderDatos> {
    private ArrayList<String> datos1,datos2,datos3;
    Context context;

    public ListSuggestionsAdapter(ArrayList<String> adatos1, ArrayList<String> adatos2, ArrayList<String> adatos3, Context context){
        this.datos1 = adatos1;
        this.datos2 = adatos2;
        this.datos3 = adatos3;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_suggestions,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.continental.travelbuddy.adapter.ListSuggestionsAdapter.ViewHolderDatos holder, int position) {
        holder.ponerDatos(datos1.get(position),datos2.get(position),datos3.get(position));
    }

    @Override
    public int getItemCount() {
        return datos1.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView list_suggestion_img;
        TextView list_suggestion_description;
        RatingBar list_suggestion_rating;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            list_suggestion_description = itemView.findViewById(R.id.list_suggestion_description);
            list_suggestion_rating = itemView.findViewById(R.id.list_suggestion_rating);
            list_suggestion_img = itemView.findViewById(R.id.list_suggestion_img);

        }
        public void ponerDatos(String a1, String a2, String a3){

            Picasso.get().load(a1)
                    .resize(500,500).placeholder(android.R.drawable.sym_def_app_icon)
                    .error(android.R.drawable.stat_notify_error).into(list_suggestion_img);

            list_suggestion_description.setText(a2);

            list_suggestion_rating.setRating(Float.parseFloat(a3));




        }
    }
}
