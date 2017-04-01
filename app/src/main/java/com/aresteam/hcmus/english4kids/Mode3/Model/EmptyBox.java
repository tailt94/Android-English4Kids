package com.aresteam.hcmus.english4kids.Mode3.Model;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by trann on 18-Jun-16.
 */
public class EmptyBox extends ImageView
{
    private int _ImagePath;

    public EmptyBox(Context context)
    {
        super(context);
    }

    public EmptyBox(Context context, int id)
    {
        super(context);
        this._ImagePath = id;
    }

    public int getImagePath()
    {
        return _ImagePath;
    }

    public void setImagePath(int id)
    {
        this._ImagePath = id;
    }

    public void setBackground()
    {
        this.setBackgroundResource(this._ImagePath);
    }

    public EmptyBox cloneObj()
    {
        return new EmptyBox(this.getContext(), this._ImagePath);
    }
}
