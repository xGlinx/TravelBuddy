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

public class ListPlacesAllAdaptar extends RecyclerView.Adapter<com.continental.travelbuddy.adapter.ListPlacesAllAdaptar.ViewHolderDatos> {
    private ArrayList<String> datos1,datos2,datos3,datos4;
    //ID, NOMBRE, CALIFICACION, FOTO
    Context context;

    public ListPlacesAllAdaptar(ArrayList<String> adatos1, ArrayList<String> adatos2, ArrayList<String> adatos3, ArrayList<String> adatos4, Context context, MyAdapterListener listener, MyAdapterListener2 listener2){
        this.datos1 = adatos1;
        this.datos2 = adatos2;
        this.datos3 = adatos3;
        this.datos4 = adatos4;
        this.context = context;
        onClickListener = listener;
        onClickListener2 = listener2;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_places_all,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.continental.travelbuddy.adapter.ListPlacesAllAdaptar.ViewHolderDatos holder, int position) {
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
        Button list_place_btn_comment,list_place_btn_comments_see;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            list_place_name = itemView.findViewById(R.id.list_place_name_all);
            list_place_val = itemView.findViewById(R.id.list_place_val_all);
            list_place_img = itemView.findViewById(R.id.list_place_img_all);

            list_place_btn_comment =itemView.findViewById(R.id.list_place_btn_comment);
            list_place_btn_comments_see =itemView.findViewById(R.id.list_place_btn_comments_see);

            list_place_btn_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.comentar(v,getAdapterPosition());
                }
            });
            list_place_btn_comments_see.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener2.ver_comentarios(v,getAdapterPosition());
                }
            });

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

    public MyAdapterListener onClickListener;
    public MyAdapterListener2 onClickListener2;
    public interface MyAdapterListener {

        void comentar(View v, int position);
    }

    public interface MyAdapterListener2 {

        void ver_comentarios(View v, int position);
    }
}
