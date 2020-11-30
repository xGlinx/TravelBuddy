package com.continental.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_inicio);
        Animation animacion1= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animacion2= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);
        TextView txt1 =findViewById(R.id.txt1);
        TextView txt2 =findViewById(R.id.txt2);
        TextView txt3 =findViewById(R.id.txt3);
        ImageView logo=findViewById(R.id.iv_logo);
        txt1.setAnimation(animacion2);
        txt2.setAnimation(animacion2);
        txt3.setAnimation(animacion2);
        logo.setAnimation(animacion1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Inicio.this,login.class);
                startActivity(intent);

            }
        }, 4000);
    }
}