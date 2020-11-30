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

public class ListCommentsAdapter extends RecyclerView.Adapter<com.continental.travelbuddy.adapter.ListCommentsAdapter.ViewHolderDatos> {
    private ArrayList<String> datos1,datos2,datos3,datos4,datos5;
    Context context;

    public ListCommentsAdapter(ArrayList<String> adatos1, ArrayList<String> adatos2, ArrayList<String> adatos3, ArrayList<String> adatos4, ArrayList<String> adatos5, Context context){
        this.datos1 = adatos1;
        this.datos2 = adatos2;
        this.datos3 = adatos3;
        this.datos4 = adatos4;
        this.datos5 = adatos5;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_comments,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.continental.travelbuddy.adapter.ListCommentsAdapter.ViewHolderDatos holder, int position) {
        holder.ponerDatos(datos1.get(position),datos2.get(position),datos3.get(position),datos4.get(position),datos5.get(position));
    }

    @Override
    public int getItemCount() {
        return datos1.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView list_comment_img;
        TextView list_comment_description, list_comment_user;
        RatingBar list_comment_rating;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            list_comment_description = itemView.findViewById(R.id.list_comment_description);
            list_comment_rating = itemView.findViewById(R.id.list_comment_rating);
            list_comment_img = itemView.findViewById(R.id.list_comment_img);
            list_comment_user = itemView.findViewById(R.id.list_comment_user);

        }
        public void ponerDatos(String a1, String a2, String a3, String a4, String a5){

            Picasso.get().load(a1)
                    .resize(500,500).placeholder(android.R.drawable.sym_def_app_icon)
                    .error(android.R.drawable.stat_notify_error).into(list_comment_img);

            list_comment_description.setText(a2);

            list_comment_rating.setRating(Float.parseFloat(a3));

            list_comment_user.setText(a4+" "+a5);
        }
    }
}
