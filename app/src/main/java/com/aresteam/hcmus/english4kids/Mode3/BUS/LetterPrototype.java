package com.aresteam.hcmus.english4kids.Mode3.BUS;

import android.content.Context;

import com.aresteam.hcmus.english4kids.Mode3.DAO.LetterDAO;
import com.aresteam.hcmus.english4kids.Mode3.Model.Letter;
import com.aresteam.hcmus.english4kids.R;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by trann on 17-Jun-16.
 */
public class LetterPrototype
{
    private static Hashtable<String, Letter> sampleLetters  = new Hashtable<String, Letter>();

    // getLetter by letterID
    //@param: Character letterID  - (exp: 'a', 'b')
    //@return: Letter
    public static Letter getLetter(String letterID)
    {
        Letter cachedLetter = sampleLetters.get(letterID);
        return (Letter) cachedLetter.cloneObj();
    }


    // InitSampleLetter: Khoi tao mau letter co san
    //@param: context
    //@return:
    public static void InitSampleLetter(Context context)
    {
        LetterDAO databaseAccess = LetterDAO.getInstance();
        List<String> listDatabase = databaseAccess.getNameImage(context);

        int n = listDatabase.size();
        for (int i = 0; i < n; i++)
        {
            String imageName = listDatabase.get(i);
            String defType = context.getResources().getString(R.string.defType);
            String defPackage = context.getResources().getString(R.string.defPackage);
            int imagePath = context.getResources().getIdentifier(imageName, defType, defPackage);

            sampleLetters.put(imageName, new Letter(context, imagePath, imageName));
        }
    }
}
