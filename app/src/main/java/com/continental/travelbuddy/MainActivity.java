package com.continental.travelbuddy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.continental.travelbuddy.helpers.NotificationSuggestions;
import com.continental.travelbuddy.ui.home.HomeFragment;
import com.continental.travelbuddy.ui.slideshow.ConfigFragment;
import com.continental.travelbuddy.ui.slideshow.InfoFragment;
import com.continental.travelbuddy.ui.slideshow.NoticiasFragment;
import com.continental.travelbuddy.ui.slideshow.RecoFragment;
import com.continental.travelbuddy.ui.slideshow.VideosFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NotificationManagerCompat notificationManagerCompat;

    NotificationCompat.Builder mBuilder;
    int mNotificationId=1;
    String channelId = "my_channel_01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //notify_suggestions();
        notificar();

        String id_fragment_suggestions = getIntent().getStringExtra("id_pasa");
        if (id_fragment_suggestions == null) {
            guardarPreferencias("0");
        } else {
            guardarPreferencias(getIntent().getStringExtra("id_pasa"));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor, new HomeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager=getSupportFragmentManager();

        if (id == R.id.menuTab) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new HomeFragment()).commit();
        } else if (id == R.id.nav_informate) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new InfoFragment()).commit();

        } else if (id == R.id.nav_videos) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new VideosFragment()).commit();

        } else if (id == R.id.nav_eventos) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new NoticiasFragment()).commit();

        }else if (id == R.id.nav_config) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new ConfigFragment()).commit();

        } else if (id == R.id.nav_cerrar) {
            Toast.makeText(this, "Hasta Luego!", Toast.LENGTH_SHORT).show();
            Intent in=new Intent(this,login.class);
            startActivity(in);

        } else if (id == R.id.nav_ayuda) {
            Uri uri = Uri.parse("https://wa.me/51989568013");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void notificar(){
        String name_comentario = "Sugerencias del dia!";
        String description_comentario = "Ingresa a ver las sugerencias de lugares que tenemos para ti!";
        NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this, null);

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.putExtra("id_pasa", "2");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            CharSequence name = "NombreCanal";

            String description = "Descripcion del canal";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, name, importance);

            notificationChannel.setDescription(description);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400});
            notificationManager.createNotificationChannel(notificationChannel);

            mBuilder = new NotificationCompat.Builder(this, channelId);
        }
        mBuilder.setSmallIcon(R.drawable.ic_main_notification).setContentTitle(name_comentario).setContentText(description_comentario);
        mBuilder.setContentIntent(pendingIntent);

        notificationManager.notify(mNotificationId,mBuilder.build());


    }

    private void guardarPreferencias(String id_fragment_suggestions){
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id_fragment_suggestions",id_fragment_suggestions);
        editor.commit();
    }
}