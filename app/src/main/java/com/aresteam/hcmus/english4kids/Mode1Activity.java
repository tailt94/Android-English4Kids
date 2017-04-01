package com.aresteam.hcmus.english4kids;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class Mode1Activity extends Activity implements Mode1Fragment.OnStageChangeListener{

    private int colorSpinner = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode1);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            Log.e("bundle != null", "");
            colorSpinner = bundle.getInt("color");
            Log.e("color", String.valueOf(colorSpinner));
        }

        Mode1Fragment stage = Mode1Fragment.newInstance(StageInfo.MODE1_STAGE0, colorSpinner);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, stage);
        ft.commit();
    }

    @Override
    public void onStageChange(Fragment currFragment, int id) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Mode1Fragment stage = Mode1Fragment.newInstance(id, colorSpinner);
        ft.remove(currFragment);
        ft.replace(R.id.fragment_container, stage);
        ft.commit();
    }
}
