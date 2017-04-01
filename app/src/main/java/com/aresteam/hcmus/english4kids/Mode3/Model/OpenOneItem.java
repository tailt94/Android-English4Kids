package com.aresteam.hcmus.english4kids.Mode3.Model;

import android.app.Activity;
import android.view.View;

import com.aresteam.hcmus.english4kids.Mode3.BUS.GameController;
import com.aresteam.hcmus.english4kids.Mode3.BUS.GameImagePrototype;
import com.aresteam.hcmus.english4kids.Mode3Activity;


/**
 * Created by trann on 19-Jun-16.
 */
public class OpenOneItem extends Item {
    public OpenOneItem() {
        name = "OpenOneItem";
    }

    @Override
    protected Item clone() {
        return new OpenOneItem();
    }

    @Override
    public void excute(Activity activity)
    {
        GameController gameController = GameController.getInstance();
        String answers = gameController.getAnswer();
        String name = GameImagePrototype.getImage(Mode3Activity.index).getName();
        String str = getMySubString(name, answers);

        int [] listIDLetter = gameController.getListIDLetter();
        int n = listIDLetter.length;

        for(int i = 0; i < n; i++)
        {
            Letter letter = (Letter)activity.findViewById(listIDLetter[i]);

            if((letter.getLetter().equals(str)) && (View.INVISIBLE != letter.getVisibility()))
            {
                letter.performClick();
                break;

            }
        }

    }


    private String getMySubString(String str1, String str2)
    {
        String str = str1;
        for (int i = 0; i < str1.length(); i++)
        {
            if(str2.charAt(i) != ' ')
            {
                StringBuilder sb = new StringBuilder(str);
                sb.setCharAt(i, ' ');
                str = sb.toString();
            }
        }

        for (int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) != ' ')
            {
                return (str.charAt(i) + "");
            }
        }


        return "";
    }
}
