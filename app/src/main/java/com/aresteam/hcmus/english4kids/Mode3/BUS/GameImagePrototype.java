package com.aresteam.hcmus.english4kids.Mode3.BUS;

import android.content.Context;
import android.util.Log;

import com.aresteam.hcmus.english4kids.Mode3.DAO.GameImageDAO;
import com.aresteam.hcmus.english4kids.Mode3.Model.GameImage;
import com.aresteam.hcmus.english4kids.R;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by trann on 17-Jun-16.
 */
public class GameImagePrototype
{
    private static LinkedHashMap<Integer, GameImage> sampleGameImg  = new LinkedHashMap<Integer, GameImage>();
    // getLetter by letterID
    //@param: Character letterID  - (exp: 'a', 'b')
    //@return: Letter
    public static GameImage getImage(Integer index)
    {
        GameImage cachedGameImg = sampleGameImg.get(index);
        return cachedGameImg.cloneObj();
    }


    // InitSampleLetter: Khoi tao mau letter co san
    //@param: context
    //@return:
    public static void InitSampleGameImg(Context context)
    {
        GameImageDAO databaseAccess = GameImageDAO.getInstance();
        List<String> listDatabase = databaseAccess.getNameImage(context);

        int n = listDatabase.size();
        for (int i = 0; i < n; i++)
        {
            String imageName = listDatabase.get(i);
            String defType = context.getResources().getString(R.string.defType);
            String defPackage = context.getResources().getString(R.string.defPackage);
            int imagePath = context.getResources().getIdentifier(imageName, defType, defPackage);

            try
            {
                sampleGameImg.put(i, new GameImage(imagePath, imageName));
            }
            catch (Exception e)
            {
                Log.w("GameDemo", "SampleGameImg Error!");
            }
        }
    }
}
