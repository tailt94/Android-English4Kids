package com.aresteam.hcmus.english4kids;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ModeChooserActivity extends Activity implements View.OnClickListener {
    ImageButton btnMode1, btnMode2, btnMode3;

    //
    private static int colorSpinner = Color.RED;
    private static int idBackground = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_chooser);

        btnMode1 = (ImageButton) findViewById(R.id.btn_mode1);
        btnMode2 = (ImageButton) findViewById(R.id.btn_mode2);
        btnMode3 = (ImageButton) findViewById(R.id.btn_mode3);


        btnMode1.setOnClickListener(this);
        btnMode2.setOnClickListener(this);
        btnMode3.setOnClickListener(this);

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
    public void onBackPressed()
    {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_mode1:
                intent = new Intent(this, Mode1Activity.class);
                intent.putExtra("color", colorSpinner);
                intent.putExtra("background", idBackground);
                startActivity(intent);
                break;
            case R.id.btn_mode2:
                intent = new Intent(this, Mode2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_mode3:
                intent = new Intent(this, Mode3Activity.class);
                intent.putExtra("background", idBackground);
                startActivity(intent);
                break;
        }
    }
}
