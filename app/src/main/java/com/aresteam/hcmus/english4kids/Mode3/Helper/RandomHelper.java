package com.aresteam.hcmus.english4kids.Mode3.Helper;

import android.content.Context;

import com.aresteam.hcmus.english4kids.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by trann on 18-Jun-16.
 */
public class RandomHelper
{
    public static List<String> getRandomLetterList(Context context, String name, int n)
    {
        int MAX_RANGE_RANDOM_CHAR = context.getResources().getInteger(R.integer.MAX_RANGE_RANDOM_CHAR);
        int MIN_RANGE_RANDOM_CHAR = context.getResources().getInteger(R.integer.MIN_RANGE_RANDOM_CHAR);

        List<String> list = new ArrayList<String>();
        int n1 = name.length();
        for (int i = 0; i < n1; i++)
        {
            char ch = name.charAt(i);
            String str = Character.toString(ch);
            list.add(str);
            //list.add(name.substring(i, i + 1));
        }

        for (int i = n1; i < n; i++)
        {
            Random ran = new Random();
            //int randomNum = rand.nextInt((max - min) + 1) + min;
            int num = ran.nextInt((MAX_RANGE_RANDOM_CHAR - MIN_RANGE_RANDOM_CHAR) + 1) + MIN_RANGE_RANDOM_CHAR;
            String str = (char)num + "";
            list.add(str);
        }

        Collections.shuffle(list);

        return list;
    }

    public static float RandomFloatNumber(float MIN_RANGE, float MAX_RANGE)
    {
        Random rand = new Random();
        float num = rand.nextFloat() * (MAX_RANGE - (MIN_RANGE)) + (MIN_RANGE);

        return num;
    }
}
