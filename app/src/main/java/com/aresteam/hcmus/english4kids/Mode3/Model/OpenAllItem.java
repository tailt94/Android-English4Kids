package com.aresteam.hcmus.english4kids.Mode3.Model;

import android.app.Activity;

import com.aresteam.hcmus.english4kids.Mode3.BUS.GameController;
import com.aresteam.hcmus.english4kids.Mode3.BUS.GameImagePrototype;
import com.aresteam.hcmus.english4kids.Mode3Activity;

/**
 * Created by trann on 19-Jun-16.
 */
public class OpenAllItem extends Item {
    public OpenAllItem() {
        name = "OpenAllItem";
    }

    @Override
    protected Item clone() {
        return new OpenAllItem();
    }

    @Override
    public void excute(Activity activity)
    {
        String imgName = GameImagePrototype.getImage(Mode3Activity.index).getName();
        String NameOfImg = imgName;

        GameController gameController = GameController.getInstance();
        int [] listIDEmptyBox = gameController.getListIDEmtpyBox();
        int [] listIDLetter = gameController.getListIDLetter();

        int nEmptyBox = listIDEmptyBox.length;
        for(int i = 0; i < nEmptyBox; i++)
        {
            EmptyBox emptyBox = (EmptyBox)activity.findViewById(listIDEmptyBox[i]);
            emptyBox.performClick();
        }

        int nLetter = listIDLetter.length;
        Letter[] listLetter = new Letter[nEmptyBox];
        for(int i = 0, count = 0; i < nLetter; i++)
        {
            Letter letter = (Letter)activity.findViewById(listIDLetter[i]);
            if(NameOfImg.contains(letter.getLetter()))
            {
                int vitri = NameOfImg.indexOf(letter.getLetter());
                listLetter[vitri] = letter;

                StringBuilder sb = new StringBuilder(NameOfImg);
                sb.setCharAt(vitri, ' ');
                NameOfImg = sb.toString();

                count++;

                if(count == NameOfImg.length())
                    break;
            }
        }


        for (int i = 0; i < nEmptyBox; i++)
        {
            listLetter[i].performClick();
        }
    }

}
