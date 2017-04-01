package com.aresteam.hcmus.english4kids.Mode3.Model;

/**
 * Created by trann on 17-Jun-16.
 */
public class GameImage
{
    private int _ImagePath;
    private String _ImageName;


    public int getImagePath()
    {
        return _ImagePath;
    }

    public String getName()
    {
        return _ImageName;
    }

    public GameImage()
    {
    }

    public GameImage(int imgPath, String name)
    {
        this._ImagePath = imgPath;
        this._ImageName = name;
    }

    public GameImage cloneObj()
    {
        return new GameImage(this._ImagePath, this._ImageName);
    }
}
