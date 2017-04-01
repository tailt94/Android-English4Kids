package com.aresteam.hcmus.english4kids;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.aresteam.hcmus.english4kids.Mode3.BUS.EmptyBoxPrototype;
import com.aresteam.hcmus.english4kids.Mode3.BUS.GameController;
import com.aresteam.hcmus.english4kids.Mode3.BUS.GameImagePrototype;
import com.aresteam.hcmus.english4kids.Mode3.BUS.LetterPrototype;
import com.aresteam.hcmus.english4kids.Mode3.Helper.RandomHelper;
import com.aresteam.hcmus.english4kids.Mode3.Model.EmptyBox;
import com.aresteam.hcmus.english4kids.Mode3.Model.GameImage;
import com.aresteam.hcmus.english4kids.Mode3.Model.Item;
import com.aresteam.hcmus.english4kids.Mode3.Model.ItemFactory;
import com.aresteam.hcmus.english4kids.Mode3.Model.Letter;
import com.aresteam.hcmus.english4kids.Mode3.Model.OpenOneItem;
import com.aresteam.hcmus.english4kids.Mode3.Model.User;

import java.util.List;
import java.util.Random;

/**
 * Created by trann on 22-Jun-16.
 */
public class Mode3Activity extends AppCompatActivity
{
    public static int index = 0;
    private int WIN_SCORES = 0;
    private int HELP_SCORES = 0;
    public static final int MAX_INDEX = 8;
    public static final float MIN_ROTATION = -20f;
    public static final float MAX_ROTATION = +20f;
    public static final int NUMBER_LETTER_OF_ROW = 8;
    public static final int MARGIN_RIGHT_OF_LETTER = 20;

    private GameController gameController = null;
    private TableLayout _LayoutEmptyBox;
    private TableLayout _LayoutAlphabet;
    private ImageView _ImageGame;
    private ImageView imageViewOpenAll;
    private TextView textViewOpenAll;
    private ImageView imageViewOpenOne;
    private TextView textViewOpenOne;
    private User user;
    private TextView textViewLevel;
    private Button buttonHelp;
    private TextView textViewScore;
    private int background = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode3);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            int xyz = bundle.getInt("color");
            background = bundle.getInt("background");
        }

        if(background == -1)
            background = R.drawable.wood;

        try {
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activityMode3);
            relativeLayout.setBackgroundResource(background);
        }
        catch (Exception e)
        {
            Log.e("Erro load background", e.toString());
        }

        LoadResouces();
        ConnectView();

        LoadGameImgToView();
        LoadEmptyBoxToView();
        LoadAlphabetToView();

        SetListener();

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, ModeChooserActivity.class);
        startActivity(intent);
    }

    private void SetListener()
    {
        View.OnClickListener oBtnHelp = btnHelpClick();
        View.OnClickListener ivOpenAll = ivOpenAllClick();
        View.OnClickListener ivOpenOne = ivOpenOne();

        buttonHelp.setOnClickListener(oBtnHelp);
        imageViewOpenAll.setOnClickListener(ivOpenAll);
        imageViewOpenOne.setOnClickListener(ivOpenOne);

    }

    public void LoadResouces()
    {
        GameImagePrototype.InitSampleGameImg(this);
        LetterPrototype.InitSampleLetter(this);
        EmptyBoxPrototype.InitSampleEmptyBox(this);

        gameController = GameController.getInstance();
        gameController.Init(GameImagePrototype.getImage(index).getName().length());
        user = new User();

        HELP_SCORES = getResources().getInteger(R.integer.HELP_SCORE);
        WIN_SCORES = getResources().getInteger(R.integer.WIN_SCORE);
        //NUMBER_LETTER_OF_ROW = getResources().getInteger(R.integer.NUMBER_LETTER_OF_ROW);
        //MARGIN_RIGHT_OF_LETTER = getResources().getInteger(R.integer.MARGIN_RIGHT_OF_LETTER);
    }

    public void LoadGameImgToView()
    {
        GameImage gameImage = GameImagePrototype.getImage(index);
        _ImageGame.setBackgroundResource(gameImage.getImagePath());
    }

    public void LoadEmptyBoxToView()
    {
        TableRow tblRow = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 5, 0);

        int n = GameImagePrototype.getImage(index).getName().length();
        for (int i = 0; i < n; i++)
        {
            EmptyBox emptyBox = EmptyBoxPrototype.getEmptyBox();
            emptyBox.setBackground();

            int id = View.generateViewId();
            emptyBox.setId(id);

            View.OnClickListener o = EmptyBoxClick();
            emptyBox.setOnClickListener(o);

            gameController.SaveIDEmptyBoxToList(i, id);
            tblRow.addView(emptyBox, params);
        }

        _LayoutEmptyBox.addView(tblRow);
    }

    public void LoadAlphabetToView()
    {
        GameImage gameImage = GameImagePrototype.getImage(index);

        int n = gameImage.getName().length();
        int row = (n / NUMBER_LETTER_OF_ROW) + (n % NUMBER_LETTER_OF_ROW == 0 ? 0 : 1);

        List<String> randomList = RandomHelper.getRandomLetterList(this, gameImage.getName(), row * NUMBER_LETTER_OF_ROW);
        //Log.e("", "");

        int k = 0;
        for (int i = 0; i < row; i ++)
        {
            TableRow tblRow = new TableRow(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, MARGIN_RIGHT_OF_LETTER, 0);
            for(int j = 0; j < NUMBER_LETTER_OF_ROW; j++, k++)
            {
                String key = randomList.get(k);

                Letter letter = LetterPrototype.getLetter(key);
                letter.setRotation(RandomHelper.RandomFloatNumber(MIN_ROTATION, MAX_ROTATION));

                View.OnClickListener o = AlphabetClick();
                letter.setOnClickListener(o);

                int id = View.generateViewId();
                letter.setId(id);
                letter.setBackground();

                gameController.SaveIDLetter(i*NUMBER_LETTER_OF_ROW + j, id);

                tblRow.addView(letter, params);
            }

            _LayoutAlphabet.addView(tblRow);
        }
    }

    private void ConnectView()
    {
        _LayoutAlphabet = (TableLayout)findViewById(R.id.tableAlphabet);
        _LayoutEmptyBox = (TableLayout)findViewById(R.id.tableEmptyBox);
        _ImageGame = (ImageView) findViewById(R.id.imageView);

        textViewLevel = (TextView)findViewById(R.id.textViewLevel);
        buttonHelp = (Button)findViewById(R.id.buttonHelp);
        textViewScore = (TextView)findViewById(R.id.textViewScore);

        imageViewOpenAll = (ImageView)findViewById(R.id.imageViewOpenAll);
        textViewOpenAll = (TextView)findViewById(R.id.textViewOpenAll);
        imageViewOpenOne = (ImageView)findViewById(R.id.imageViewOpenOne);
        textViewOpenOne = (TextView)findViewById(R.id.textViewOpenOne);
    }

    private View.OnClickListener EmptyBoxClick()
    {
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(gameController.CheckState(v))
                {
                    RemoveAlphabetFromEmptyBox(v);
                    int idLetter = gameController.FindIDLetterHidden(v.getId());
                    DisplayAlphabetHidden(idLetter);
                }
            }
        };


        return onClickListener;
    }

    private View.OnClickListener AlphabetClick()
    {
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(gameController.isClick())
                {
                    HiddenLetter(v);
                    int idEmptyBox = gameController.FindIDEmptyBox();
                    AddLetterToEmptyBox(idEmptyBox, v.getId());

                    gameController.SaveState(v);
                }

                //Neu thang Game
                if(gameController.KiemTraThangThua(GameImagePrototype.getImage(index).getName()))
                {
                    MediaPlayer mp = MediaPlayer.create(Mode3Activity.this, R.raw.correct);
                    mp.start();
                    Toast.makeText(Mode3Activity.this, "Thang!", Toast.LENGTH_SHORT).show();
                    index++;
                    if(index >= MAX_INDEX) {
                        Toast.makeText(Mode3Activity.this, "Kết thúc!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    gameController.Init(GameImagePrototype.getImage(index).getName().length());

                    updateItemList();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Remove empty box
                            _LayoutEmptyBox.removeAllViews();

                            //Remove letter
                            _LayoutAlphabet.removeAllViews();

                            //Remove image view
                            LoadGameImgToView();
                            LoadAlphabetToView();
                            LoadEmptyBoxToView();
                        }
                    }, 3000);

                }
                else if(gameController.CauTraLoiChuaChinhXac())
                {
                    //Animation EmptyBox
                    AnimationForEmptyBox();
                    MediaPlayer mp = MediaPlayer.create(Mode3Activity.this, R.raw.wrong);
                    mp.start();
                }
            }
        };


        return onClickListener;
    }

    private void AnimationForEmptyBox() {
        int [] listIdEmptyBox = gameController.getListIDEmtpyBox();
        int n = listIdEmptyBox.length;

        ObjectAnimator[] objectAnimators = new ObjectAnimator[n];
        AnimatorSet animSetXY = new AnimatorSet();
        Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate_letter);

        for (int i = 0; i < n; i++)
        {
            EmptyBox emptyBox = (EmptyBox)findViewById(listIdEmptyBox[i]);
            //objectAnimators[i] = RotateEmptyBox(emptyBox);
            emptyBox.startAnimation(an);
        }

        /*animSetXY.playTogether(objectAnimators);
        animSetXY.setDuration(1500);
        animSetXY.start();*/
    }

/*    private ObjectAnimator RotateEmptyBox(final View v) {
        ObjectAnimator rot = ObjectAnimator.ofFloat(v, "rotation", 15f, -15f);
        rot.setRepeatCount(2);
        return rot;
    }*/

    private void updateItemList()
    {
        //Recieve item
        user.receiveWinScores(WIN_SCORES);
        textViewLevel.setText("LEVEL: " + String.valueOf(index));
        textViewScore.setText("SCORE: " + String.valueOf(user.getScores()));

        List<String> itemNames = ItemFactory.getAllItemName();
        int maxIndex = itemNames.size();
        Random random = new Random();
        int index = random.nextInt(maxIndex);

        String itemName = itemNames.get(index);
        Item item = ItemFactory.createItem(itemName);
        user.receiveItem(item);

        textViewOpenOne.setText(String.valueOf(user.countItemByName("OpenOneItem")));
        textViewOpenAll.setText(String.valueOf(user.countItemByName("OpenAllItem")));

        /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("MESSAGE");
        String message = "Đáp án chính xác nhận " + String.valueOf(WIN_SCORES) + " điểm !";
        message = message.concat("\n");
        message = message.concat("Nhận được 1 " + itemName + " !");
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();*/
    }

    private View.OnClickListener btnHelpClick()
    {
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (user.getScores() < HELP_SCORES) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Mode3Activity.this);
                    builder.setTitle("MESSAGE");
                    builder.setMessage("Không đủ điểm để thực hiện !");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    return;
                }

                OpenOneItem item = new OpenOneItem();
                item.excute(Mode3Activity.this);

                user.setScores(user.getScores() - HELP_SCORES);
                textViewScore.setText("SCORE: " + String.valueOf(user.getScores()));
            }
        };


        return onClickListener;
    }

    private View.OnClickListener ivOpenOne()
    {
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (user.countItemByName("OpenOneItem") == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Mode3Activity.this);
                    builder.setTitle("MESSAGE");
                    builder.setMessage("Không đủ item để thực hiện !");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    return;
                }

                //

                user.useItem("OpenOneItem", Mode3Activity.this);

                textViewOpenOne.setText(String.valueOf(user.countItemByName("OpenOneItem")));
            }
        };


        return onClickListener;
    }

    private View.OnClickListener ivOpenAllClick()
    {
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (user.countItemByName("OpenAllItem") == 0)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Mode3Activity.this);
                    builder.setTitle("MESSAGE");
                    builder.setMessage("Không đủ item để thực hiện !");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    return;
                }



                user.useItem("OpenAllItem", Mode3Activity.this);
                textViewOpenAll.setText(String.valueOf(user.countItemByName("OpenAllItem")));
            }
        };


        return onClickListener;
    }

    private void HiddenLetter(View v)
    {
        Letter letter = (Letter)v;
        v.setVisibility(View.INVISIBLE);
    }

    private void DisplayAlphabetHidden(int idAlphabet)
    {
        Letter myLetter = (Letter)findViewById(idAlphabet);
        myLetter.setVisibility(View.VISIBLE);
    }

    private void RemoveAlphabetFromEmptyBox(View v)
    {
        int imagePath = EmptyBoxPrototype.getEmptyBox().getImagePath();
        v.setBackgroundResource(imagePath);
    }

    private void AddLetterToEmptyBox(int idEmptyBox, int idLetter)
    {
        Letter letter = (Letter) findViewById(idLetter);
        EmptyBox emptyBox = (EmptyBox) findViewById(idEmptyBox);

        emptyBox.setBackgroundResource(letter.getImagePath());

    }

}
