package com.aresteam.hcmus.english4kids;


import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mode2Fragment extends Fragment implements View.OnClickListener {

    private int screenWidth;
    private int screenHeight;
    private int imageHolderSize;
    private int imageSize;
    private int systemButtonSize;

    private int stageCount;

    private int stageId;
    private Mode2Manager gameStage;
    private int charCount;
    private int[] charIds;

    private RelativeLayout rootLayout;
    private GridLayout answerHolder;
    private RelativeLayout[] imageHolders;
    private ImageView[] charImages;

    private ImageButton btnLeft, btnRight, btnCheck;
    private TextView currTimer, bestTimer;
    private DisplayMetrics displayMetrics;

    private int currTime = 0;
    private int bestTime;
    private boolean isCompleted = false;

    private OnStageChangeListener mCallback;


    public interface OnStageChangeListener {
        void onStageChange(Fragment currFragment, int id);
    }

    public Mode2Fragment() {
        // Required empty public constructor
    }

    public static Mode2Fragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("Id", id);

        Mode2Fragment fragment = new Mode2Fragment();
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
        stageCount = Mode2Manager.stages.length;
        gameStage = Mode2Manager.stages[stageId];
        charCount = gameStage.getCharCount();
        charIds = gameStage.getCharResourceIds();
        bestTime = ScoreManager.LoadScore(this.getActivity(), stageId+10);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        imageHolderSize = (int) (screenHeight * 0.13);
        imageSize = (int) (screenHeight * 0.11);
        systemButtonSize = (int) (screenHeight * 0.13);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mode2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        rootLayout = (RelativeLayout) getView();
        if(rootLayout != null) {
            this.answerHolder = (GridLayout) rootLayout.findViewById(R.id.answerHolder);
            this.btnLeft = (ImageButton) rootLayout.findViewById(R.id.btnLeft);
            this.btnRight = (ImageButton) rootLayout.findViewById(R.id.btnRight);
            this.btnCheck = (ImageButton) rootLayout.findViewById(R.id.btnCheck);
            this.currTimer = (TextView) rootLayout.findViewById(R.id.currTimer);
            this.bestTimer = (TextView) rootLayout.findViewById(R.id.bestTimer);

            setBestTimer();

            btnLeft.setOnClickListener(this);
            btnRight.setOnClickListener(this);
            btnCheck.setOnClickListener(this);

            btnLeft.getLayoutParams().width = systemButtonSize;
            btnLeft.getLayoutParams().height = systemButtonSize;
            btnRight.getLayoutParams().width = systemButtonSize;
            btnRight.getLayoutParams().height = systemButtonSize;
            btnCheck.getLayoutParams().width = systemButtonSize;
            btnCheck.getLayoutParams().height = systemButtonSize;

            initStage();

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLeft:
                if(stageId == StageInfo.MODE2_STAGE0) {
                    stageId = stageCount - 1;
                }
                else {
                    stageId--;
                }

                mCallback.onStageChange(this, stageId);
                break;

            case R.id.btnRight:
                if(stageId == (stageCount - 1)) {
                    stageId = StageInfo.MODE2_STAGE0;
                }
                else {
                    stageId++;
                }
                mCallback.onStageChange(this, stageId);
                break;

            case R.id.root_layout:
                MediaPlayer mPlayer = MediaPlayer.create(this.getActivity(), gameStage.getSound());
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.start();
                break;

            case R.id.btnCheck:
                //boolean isEnded = true;

                if(!isCompleted) {
                    isCompleted = true;

                    for(int i = 0; i < charCount; i++) {
                        ImageView img = (ImageView) imageHolders[i].getChildAt(0);
                        if(img == null) {
                            isCompleted = false;
                            break;
                        }
                        else {
                            int imgResource = getDrawableId(img);
                            if(imgResource != Mode2Manager.stages[stageId].getCharResourceIds()[i]) {
                                isCompleted = false;
                                break;
                            }
                        }
                    }

                    //Game is completed
                    if(isCompleted) {
                        Toast.makeText(this.getActivity(), "COMPLETE", Toast.LENGTH_SHORT).show();
                        rootLayout.setOnClickListener(this);
                        if(currTime < bestTime) {
                            ScoreManager.SaveScore(this.getActivity(), stageId+10, currTime);
                        }

                    }
                }
                break;
            default:
                break;
        }
    }

    private void setBestTimer() {
        int hour = bestTime/3600;
        int minutes = (bestTime%3600)/60;
        int secs = bestTime%60;
        String time = String.format("Best: %02d:%02d", minutes, secs);
        bestTimer.setText(time);
    }

    private void initStage() {
        DragAndDrop.LayoutDragListener mLayoutDragListener = new DragAndDrop.LayoutDragListener();

        rootLayout.setOnDragListener(mLayoutDragListener);
        rootLayout.setBackgroundResource(gameStage.getBackground());
        answerHolder.setColumnCount(charCount);

        addImageHolders();
        addCharImages();


            runTimer(currTimer);
    }

    private void addCharImages() {

        int randomX, randomY;

        DragAndDrop.ImageTouchListener mImageTouchListener = new DragAndDrop.ImageTouchListener();

        charImages = new ImageView[charCount];
        for(int i = 0; i < charCount; i++) {
            charImages[i] = new ImageView(this.getActivity());
            charImages[i].setImageResource(charIds[i]);
            charImages[i].setTag(charIds[i]);

            rootLayout.addView(charImages[i]);
            charImages[i].getLayoutParams().width = imageSize;
            charImages[i].getLayoutParams().height = imageSize;

            randomX = (int) (Math.random() * (screenWidth - 300));
            randomY = (int) (Math.random() * (screenHeight - 300));

            charImages[i].setX(randomX);
            charImages[i].setY(randomY);
            charImages[i].setOnTouchListener(mImageTouchListener);
        }
    }

    private void addImageHolders() {
        DragAndDrop.ImageHolderDragListener mImageHolderDragListener = new DragAndDrop.ImageHolderDragListener();
        imageHolders = new RelativeLayout[charCount];
        for(int i = 0; i < charCount; i++) {
            imageHolders[i] = new RelativeLayout(this.getActivity());
            answerHolder.addView(imageHolders[i]);
            imageHolders[i].setGravity(Gravity.CENTER);
            imageHolders[i].getLayoutParams().width = imageHolderSize;
            imageHolders[i].getLayoutParams().height = imageHolderSize;
            imageHolders[i].setBackgroundColor(getResources().getColor(R.color.colorBackground));
            imageHolders[i].setOnDragListener(mImageHolderDragListener);
        }
    }

    private int getDrawableId(ImageView img) {
        return (Integer) img.getTag();
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
                if(!isCompleted) {
                    currTime++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


}
