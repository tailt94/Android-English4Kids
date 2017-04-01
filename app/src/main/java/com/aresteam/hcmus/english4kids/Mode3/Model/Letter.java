package com.aresteam.hcmus.english4kids.Mode3.Model;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by trann on 17-Jun-16.
 */
public class Letter extends ImageView
{
    private int _ImagePath;
    private String _LetterID;

    public Letter(Context context)
    {
        super(context);
    }

    public Letter(Context context, int path, String id)
    {
        super(context);

        this._ImagePath = path;
        this._LetterID = id;
    }

    public int getImagePath()
    {
        return _ImagePath;
    }

    public String getLetter()
    {
        return _LetterID;
    }

    public void setImagePath(int id)
    {
        _ImagePath = id;
    }

    public void setLetter(String ch)
    {
        _LetterID = ch;
    }

    public Letter cloneObj()
    {
        return new Letter(this.getContext(), this._ImagePath, this._LetterID);
    }

    public void setBackground() {
        this.setBackgroundResource(this._ImagePath);
    }
}
