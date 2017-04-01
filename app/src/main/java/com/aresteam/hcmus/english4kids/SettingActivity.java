package com.aresteam.hcmus.english4kids;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.aresteam.hcmus.english4kids.Setting.Background;
import com.aresteam.hcmus.english4kids.Setting.BackgroundSpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    RelativeLayout linearLayoutSetting;

    List<Background> listColor;
    List<Background> backgrounds;
    List<Integer> idColor;
    Spinner SpinnerColorSetting;
    Spinner SpinnerBackgroundSetting;

    Button buttonSettingOK;
    Button buttonSettingCancel;

    Background startBackground;
    Background settingBackground;

    private int startColor;
    private int settingColor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ConnectView();
        Init();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            int color = bundle.getInt("color");
            int bg = bundle.getInt("background");

            startBackground = new Background(bg);
            settingBackground = new Background(bg);

            startColor = color;
            settingColor = color;

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            Resources resources = getResources();
            Drawable drawable = resources.getDrawable(startBackground.getImg());
            linearLayoutSetting.setBackground(drawable);
        }


        BackgroundSpinnerAdapter colorAdapter = new BackgroundSpinnerAdapter(SettingActivity.this, R.layout.custom_setting_spinner, listColor);
        SpinnerColorSetting.setAdapter(colorAdapter);

        SpinnerColorSetting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //settingBackground = listColor.get(position);
                settingColor = idColor.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        BackgroundSpinnerAdapter backgroundSpinnerAdapter = new BackgroundSpinnerAdapter(SettingActivity.this, R.layout.custom_setting_spinner, backgrounds);
        SpinnerBackgroundSetting.setAdapter(backgroundSpinnerAdapter);
        SpinnerBackgroundSetting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                settingBackground = backgrounds.get(position);
                //settingColor = idColor.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonSettingOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, HomeScreenActivity.class);
                intent.putExtra("color", settingColor);
                intent.putExtra("background", settingBackground.getImg());
                startActivity(intent);
            }
        });

        buttonSettingCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, HomeScreenActivity.class);
                intent.putExtra("color", startColor);
                intent.putExtra("background", startBackground.getImg());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("color", startColor);
        intent.putExtra("background", startBackground.getImg());
        startActivity(intent);
    }

    private void ConnectView()
    {
        linearLayoutSetting = (RelativeLayout)findViewById(R.id.linearLayoutSetting);
        SpinnerColorSetting = (Spinner)findViewById(R.id.spinnerColor);
        SpinnerBackgroundSetting = (Spinner)findViewById(R.id.spinnerBackgroundSetting);
        buttonSettingOK = (Button)findViewById(R.id.buttonSettingOK);
        buttonSettingCancel = (Button)findViewById(R.id.buttonSettingCancel);
    }

    private void Init()
    {
        listColor = new ArrayList<Background>();

        listColor.add(new Background(R.drawable.red));
        listColor.add(new Background(R.drawable.orange));
        listColor.add(new Background(R.drawable.yellow));
        listColor.add(new Background(R.drawable.green));
        listColor.add(new Background(R.drawable.blue));
        listColor.add(new Background(R.drawable.indigo));
        listColor.add(new Background(R.drawable.violet));

        backgrounds = new ArrayList<Background>();
        backgrounds.add(new Background(R.drawable.bg_00));
        backgrounds.add(new Background(R.drawable.bg_01));
        backgrounds.add(new Background(R.drawable.bg_02));
        backgrounds.add(new Background(R.drawable.bg_03));
        backgrounds.add(new Background(R.drawable.bg_04));
        backgrounds.add(new Background(R.drawable.bg_05));
        backgrounds.add(new Background(R.drawable.bg_06));
        backgrounds.add(new Background(R.drawable.bg_07));
        backgrounds.add(new Background(R.drawable.bg_08));
        backgrounds.add(new Background(R.drawable.bg_09));

        idColor = new ArrayList<Integer>();
        idColor.add(Color.RED);
        idColor.add(Color.rgb(255, 165, 0));
        idColor.add(Color.YELLOW);
        idColor.add(Color.GREEN);
        idColor.add(Color.BLUE);
        idColor.add(Color.rgb(75, 0, 130));
        idColor.add(Color.rgb(238, 130, 238));
    }

}
