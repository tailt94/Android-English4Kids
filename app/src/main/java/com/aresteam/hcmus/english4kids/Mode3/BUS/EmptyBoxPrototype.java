package com.aresteam.hcmus.english4kids.Mode3.BUS;

import android.content.Context;

import com.aresteam.hcmus.english4kids.Mode3.Model.EmptyBox;
import com.aresteam.hcmus.english4kids.R;


/**
 * Created by trann on 18-Jun-16.
 */
public class EmptyBoxPrototype
{
    private static EmptyBox _EmptyBox = null;

    public static void InitSampleEmptyBox(Context context)
    {
        String defType = context.getResources().getString(R.string.defType);
        String defPackage = context.getResources().getString(R.string.defPackage);
        String imageName = context.getResources().getString(R.string.empty);
        int imagePath = context.getResources().getIdentifier(imageName, defType, defPackage);

        _EmptyBox = new EmptyBox(context);
        _EmptyBox.setImagePath(imagePath);
    }

    public static EmptyBox getEmptyBox()
    {
        return _EmptyBox.cloneObj();
    }
}
