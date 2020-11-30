package com.continental.travelbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class DetailEvents extends Activity {
    TextView name;
    TextView district;
    TextView description;
    TextView detail;
    TextView time;
    ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailevents_popup);

        name = findViewById(R.id.txt_event_detail_name);
        district = findViewById(R.id.txt_event_detail_district);
        description = findViewById(R.id.txt_event_detail_description);
        detail = findViewById(R.id.txt_event_detail_detail);
        time = findViewById(R.id.txt_event_detail_time);
        image = findViewById(R.id.iv_event_detail_imagen);

        String name_event = getIntent().getStringExtra("name_event");
        String district_event = getIntent().getStringExtra("district_event");
        String description_event = getIntent().getStringExtra("description_event");
        String detail_event = getIntent().getStringExtra("detail_event");
        String time_event = getIntent().getStringExtra("time_event");
        String image_event = getIntent().getStringExtra("image_event");

        name.setText(name_event);
        district.setText(district_event);
        description.setText(description_event);
        detail.setText(detail_event);
        time.setText(time_event);

        Picasso.get().load(image_event)
                .resize(500,500).placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.stat_notify_error).into(image);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9), (int)(height*.8));
    }
}
