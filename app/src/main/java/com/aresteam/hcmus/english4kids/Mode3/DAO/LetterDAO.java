package com.aresteam.hcmus.english4kids.Mode3.DAO;

import android.content.Context;

import com.aresteam.hcmus.english4kids.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by trann on 17-Jun-16.
 */
public class LetterDAO
{
    private static LetterDAO instance;


    private LetterDAO()
    {

    }

    public List<String> getNameImage(Context context)
    {
        List<String> list = new ArrayList<>();
        String [] arr = context.getResources().getStringArray(R.array.alphabetArray);
        list = Arrays.asList(arr);

        return list;
    }

    public static LetterDAO getInstance()
    {
        if (instance == null)
        {
            instance = new LetterDAO();
        }
        return instance;
    }
}
