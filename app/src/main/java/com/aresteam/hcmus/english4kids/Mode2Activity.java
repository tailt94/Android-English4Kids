package com.aresteam.hcmus.english4kids;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Mode2Activity extends Activity implements Mode2Fragment.OnStageChangeListener {

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode2);

        Mode2Fragment stage = Mode2Fragment.newInstance(StageInfo.MODE2_STAGE0);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, stage);
        ft.commit();
    }

    @Override
    public void onStageChange(Fragment currFragment, int id) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Mode2Fragment stage = Mode2Fragment.newInstance(id);
        ft.remove(currFragment);
        ft.replace(R.id.fragment_container, stage);
        ft.commit();
    }
}
