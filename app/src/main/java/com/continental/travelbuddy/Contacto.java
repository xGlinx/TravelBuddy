package com.continental.travelbuddy;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Contacto extends AppCompatActivity {
    Button btn1, btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        btn7=(Button)findViewById(R.id.btn7);
        btn8=(Button)findViewById(R.id.btn8);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i= new Intent(Intent.ACTION_CALL, Uri.parse("tel:064232230"));
                if(ActivityCompat.checkSelfPermission(
                        Contacto.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                        Contacto.this,Manifest
                                .permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Contacto.this,new String[]
                            { Manifest.permission.CALL_PHONE,},1000);
                }else{
                };
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i= new Intent(Intent.ACTION_CALL, Uri.parse("tel:064233333"));
                if(ActivityCompat.checkSelfPermission(
                        Contacto.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                        Contacto.this,Manifest
                                .permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Contacto.this,new String[]
                            { Manifest.permission.CALL_PHONE,},1000);
                }else{
                };
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i= new Intent(Intent.ACTION_CALL, Uri.parse("tel:064231961"));
                if(ActivityCompat.checkSelfPermission(
                        Contacto.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                        Contacto.this,Manifest
                                .permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Contacto.this,new String[]
                            { Manifest.permission.CALL_PHONE,},1000);
                }else{
                };
                startActivity(i);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i= new Intent(Intent.ACTION_CALL, Uri.parse("tel:064232222"));
                if(ActivityCompat.checkSelfPermission(
                        Contacto.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                        Contacto.this,Manifest
                                .permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Contacto.this,new String[]
                            { Manifest.permission.CALL_PHONE,},1000);
                }else{
                };
                startActivity(i);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i= new Intent(Intent.ACTION_CALL, Uri.parse("tel:064231832"));
                if(ActivityCompat.checkSelfPermission(
                        Contacto.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                        Contacto.this,Manifest
                                .permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Contacto.this,new String[]
                            { Manifest.permission.CALL_PHONE,},1000);
                }else{
                };
                startActivity(i);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i= new Intent(Intent.ACTION_CALL, Uri.parse("tel:105"));
                if(ActivityCompat.checkSelfPermission(
                        Contacto.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                        Contacto.this,Manifest
                                .permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Contacto.this,new String[]
                            { Manifest.permission.CALL_PHONE,},1000);
                }else{
                };
                startActivity(i);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i= new Intent(Intent.ACTION_CALL, Uri.parse("tel:064233340"));
                if(ActivityCompat.checkSelfPermission(
                        Contacto.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                        Contacto.this,Manifest
                                .permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Contacto.this,new String[]
                            { Manifest.permission.CALL_PHONE,},1000);
                }else{
                };
                startActivity(i);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i= new Intent(Intent.ACTION_CALL, Uri.parse("tel:064219851"));
                if(ActivityCompat.checkSelfPermission(
                        Contacto.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                        Contacto.this,Manifest
                                .permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Contacto.this,new String[]
                            { Manifest.permission.CALL_PHONE,},1000);
                }else{
                };
                startActivity(i);
            }
        });
    }
}