package com.aresteam.hcmus.english4kids;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnPlay, btnSettings, btnInfo;

    //spinner color
    private static int colorSpinner = Color.RED;
    private static int idBackground = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnSettings = (ImageButton) findViewById(R.id.btnSettings);
        btnInfo = (ImageButton) findViewById(R.id.btnInfo);

        btnPlay.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnInfo.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            colorSpinner = bundle.getInt("color");
            idBackground = bundle.getInt("background");
        }

        if(idBackground == -1)
            idBackground = R.drawable.wood;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.btnPlay:
                intent = new Intent(this, ModeChooserActivity.class);
                intent.putExtra("color", colorSpinner);
                intent.putExtra("background", idBackground);
                startActivity(intent);
                break;
            case R.id.btnSettings:
                intent = new Intent(this, SettingActivity.class);
                intent.putExtra("color", colorSpinner);
                intent.putExtra("background", idBackground);
                startActivity(intent);
                break;
            case R.id.btnInfo:
                intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
