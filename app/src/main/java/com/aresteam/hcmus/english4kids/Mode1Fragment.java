package com.aresteam.hcmus.english4kids;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mode1Fragment extends Fragment implements View.OnClickListener {
    private final int SYSTEM_BUTTON_MARGIN = 20;
    private final int LINE_COLOR = Color.RED;
    private final int STROKE_WIDTH = 20;

    private int screenWidth;
    private int screenHeight;
    private int systemButtonSize;
    private int playButtonSize;

    private Mode1Manager gameStage;
    private int stageId;
    private int btnCount;
    private int stageCount;
    private int colorId;
    private int currBtnIndex = 0;

    private TextView currTimer, bestTimer;
    private int currTime = 0;
    private int bestTime;

    private ImageButton btnLeft, btnRight;
    private Button[] buttons;
    private RelativeLayout rootLayout;
    private ArrayList<PointF> drawPoints;
    private OnStageChangeListener mCallback;

    public interface OnStageChangeListener {
        void onStageChange(Fragment currFragment, int id);
    }

    private class MyRelativeLayout extends RelativeLayout {
        Paint mPaint;
        public MyRelativeLayout(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setColor(colorId);
            mPaint.setStrokeWidth(STROKE_WIDTH);
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
            if(isCompleted()) {
                mPaint.setColor(Color.TRANSPARENT);
            }

            int c = drawPoints.size() * 2;
            float[] f = new float[c];
            for(int i = 0; i < c; i++) {
                if(i % 2 == 0) {
                    f[i] = drawPoints.get(i/2).x;
                } else {
                    f[i] = drawPoints.get(i/2).y;
                }
            }

            canvas.drawLines(f, mPaint);
            super.dispatchDraw(canvas);

        }
    }


    public Mode1Fragment() {
        // Required empty public constructor
    }

    public static Mode1Fragment newInstance(int stageId, int colorId) {
        
        Bundle args = new Bundle();
        args.putInt("Id", stageId);
        args.putInt("Color", colorId);
        
        Mode1Fragment fragment = new Mode1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnStageChangeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnStageChangeListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stageId = getArguments().getInt("Id");
        colorId = getArguments().getInt("Color");
        btnCount = Mode1Manager.stages[stageId].getBtnCount();
        stageCount = Mode1Manager.stages.length;
        gameStage = Mode1Manager.stages[stageId];
        drawPoints = new ArrayList<>();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        systemButtonSize = (int) (screenHeight * 0.13);
        playButtonSize = (int) (screenHeight * 0.09);

        bestTime = ScoreManager.LoadScore(this.getActivity(), stageId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mode1, container, false);
        return new MyRelativeLayout(this.getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        rootLayout = (RelativeLayout) getView();
        if(rootLayout != null) {
            rootLayout.setBackgroundResource(gameStage.getBackground());

            addPlayButtons();

            addSystemButtons();

            addTimer();

        }
    }

    @Override
    public void onClick(View v) {
        if(v == btnLeft) {

            if(stageId == StageInfo.MODE1_STAGE0) {
                stageId = stageCount-1;
            } else { stageId--; }

            mCallback.onStageChange(this, stageId);
        } else if(v == btnRight) {

            if(stageId == stageCount-1) {
                stageId = StageInfo.MODE1_STAGE0;
            } else { stageId++; }

            mCallback.onStageChange(this, stageId);
        } else if(v == rootLayout) {
            MediaPlayer mPlayer = MediaPlayer.create(this.getActivity(), gameStage.getSound());
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.start();
        } else {
            int soundId = rootLayout.indexOfChild(v);
            MediaPlayer mPlayer = MediaPlayer.create(this.getActivity(), StageInfo.sounds[soundId]);
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.start();

            int curr = currBtnIndex;
            int next;
            if(curr == btnCount-1) {
                next = 0;
            }
            else {
                next = curr + 1;
            }
            if(currBtnIndex < btnCount) {
                if(v == buttons[next]) {

                    float x = buttons[curr].getX() + playButtonSize/2;
                    float y = buttons[curr].getY() + playButtonSize/2;
                    PointF p = new PointF(x, y);
                    drawPoints.add(p);

                    x = buttons[next].getX() + playButtonSize/2;
                    y = buttons[next].getY() + playButtonSize/2;
                    p = new PointF(x, y);
                    drawPoints.add(p);


                    currBtnIndex++;
                    rootLayout.invalidate();

                    //Game is completed
                    if(isCompleted()) {
                        rootLayout.setBackgroundResource(gameStage.getBackgroundComplete());
                        hideAllPlayButtons();
                        Toast.makeText(this.getActivity(), "COMPLETE", Toast.LENGTH_SHORT).show();
                        rootLayout.setOnClickListener(this);

                        if(currTime < bestTime) {
                            ScoreManager.SaveScore(this.getActivity(), stageId, currTime);
                        }
                    }
                }
            }
        }
    }

    private void addTimer() {
        currTimer = new TextView(this.getActivity());
        bestTimer = new TextView(this.getActivity());

        rootLayout.addView(currTimer);

        int id = View.generateViewId();
        bestTimer.setId(id);

        rootLayout.addView(bestTimer);

        RelativeLayout.LayoutParams paramsBestTimer = (RelativeLayout.LayoutParams)bestTimer.getLayoutParams();
        paramsBestTimer.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        paramsBestTimer.addRule(RelativeLayout.CENTER_HORIZONTAL);
        paramsBestTimer.addRule(RelativeLayout.LEFT_OF, btnRight.getId());
        bestTimer.setLayoutParams(paramsBestTimer);

        RelativeLayout.LayoutParams paramsCurrTimer = (RelativeLayout.LayoutParams)currTimer.getLayoutParams();
        paramsCurrTimer.addRule(RelativeLayout.ALIGN_LEFT, bestTimer.getId());
        paramsCurrTimer.addRule(RelativeLayout.BELOW, bestTimer.getId());
        currTimer.setLayoutParams(paramsCurrTimer);

        int hour = bestTime/3600;
        int minutes = (bestTime%3600)/60;
        int secs = bestTime%60;
        String time = String.format("Best: %02d:%02d", minutes, secs);
        bestTimer.setText(time);

        runTimer(currTimer);
    }

    private boolean isCompleted() {
        return (currBtnIndex == btnCount);
    }

    private void hideAllPlayButtons() {
        for(Button button : buttons) {
            button.setVisibility(View.INVISIBLE);
        }
    }

    private void addSystemButtons() {
        btnLeft = new ImageButton(this.getActivity());
        btnLeft.setBackgroundResource(R.drawable.btn_previous_stage);
        rootLayout.addView(btnLeft);
        RelativeLayout.LayoutParams paramsLeft = (RelativeLayout.LayoutParams) btnLeft.getLayoutParams();
        paramsLeft.width = systemButtonSize;
        paramsLeft.height = systemButtonSize;
        paramsLeft.setMargins(SYSTEM_BUTTON_MARGIN, SYSTEM_BUTTON_MARGIN, 0, 0);
        paramsLeft.addRule(RelativeLayout.ALIGN_PARENT_START);
        paramsLeft.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        btnLeft.setLayoutParams(paramsLeft);
        btnLeft.setOnClickListener(this);

        btnRight = new ImageButton(this.getActivity());
        btnRight.setBackgroundResource(R.drawable.btn_next_stage);
        rootLayout.addView(btnRight);
        RelativeLayout.LayoutParams paramsRight = (RelativeLayout.LayoutParams) btnRight.getLayoutParams();
        paramsRight.width = systemButtonSize;
        paramsRight.height = systemButtonSize;
        paramsRight.setMargins(0, SYSTEM_BUTTON_MARGIN, SYSTEM_BUTTON_MARGIN, 0);
        paramsRight.addRule(RelativeLayout.ALIGN_PARENT_END);
        paramsRight.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        btnRight.setLayoutParams(paramsRight);
        btnRight.setOnClickListener(this);
    }

    private void addPlayButtons() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        buttons = new Button[btnCount];
        for(int i = 0; i < btnCount; i++) {
            buttons[i] = new Button(this.getActivity());
            buttons[i].setBackgroundResource(R.drawable.circle);
            rootLayout.addView(buttons[i]);
            buttons[i].getLayoutParams().width = playButtonSize;
            buttons[i].getLayoutParams().height = playButtonSize;
            String btnText = Integer.toString(i + 1);
            buttons[i].setText(btnText);

            PointF p = gameStage.getBtnPoints().get(i);
            float x = screenWidth * p.x / 1920;
            float y = screenHeight * p.y / 1080;

            buttons[i].setX(x - playButtonSize/2);
            buttons[i].setY(y - playButtonSize/2);
            buttons[i].setOnClickListener(this);
        }
    }

    private void runTimer(final TextView clock) {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = currTime/3600;
                int minutes = (currTime%3600)/60;
                int secs = currTime%60;
                String time = String.format("Current: %02d:%02d", minutes, secs);
                clock.setText(time);
                if(!isCompleted()) {
                    currTime++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
