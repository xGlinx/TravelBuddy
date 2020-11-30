package com.continental.travelbuddy.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.continental.travelbuddy.Contacto;
import com.continental.travelbuddy.Historial;
import com.continental.travelbuddy.Mapa;
import com.continental.travelbuddy.R;
import com.continental.travelbuddy.eventosProximos;
import com.continental.travelbuddy.map;
import com.continental.travelbuddy.ranking;
import com.continental.travelbuddy.rutasPredet;

public class MenuFragment extends Fragment {
    Button btnranking,btnmapa,btnevento,btnruta,btnhistorial,btncontacto;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        btnmapa=(Button)root.findViewById(R.id.btn_mapa);
        btnranking=(Button)root.findViewById(R.id.btn_ranking);
        btnhistorial=(Button)root.findViewById(R.id.btn_historial);
        btncontacto=(Button)root.findViewById(R.id.btn_contacto);
        btnevento=(Button)root.findViewById(R.id.btn_eventos);
        btnmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarMapa();
            }
        });
        btnruta=(Button)root.findViewById(R.id.btn_rutas);
        btnhistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarHistorial();
            }
        });
        btnranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarRanking();
            }
        });
        btnevento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarEventos();
            }
        });
        btnruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarRutas();
            }
        });
        btncontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarContactos();
            }
        });
        btnhistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarHistorial();
            }
        });
        return root;
    }

    public void cargarRanking(){
        Intent siguiente = new Intent(getContext(), ranking.class);
        startActivity(siguiente);
    }
    public  void cargarMapa(){
        Intent in1 = new Intent(getContext(), Mapa.class);
        startActivity(in1);
    }
    public void cargarEventos(){
        Intent in1 = new Intent(getContext(), eventosProximos.class);
        startActivity(in1);
    }
    public void cargarRutas(){
        Intent in1 = new Intent(getContext(), Mapa.class);

        startActivity(in1);
    }
    public void cargarHistorial(){
        Intent in1 = new Intent(getContext(), Historial.class);
        startActivity(in1);
    }
    public  void cargarContactos(){
        Intent in1 = new Intent(getContext(), Contacto.class);
        startActivity(in1);
    }
}
