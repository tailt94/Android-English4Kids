package com.aresteam.hcmus.english4kids.Mode3.Model;

import android.app.Activity;


/**
 * Created by trann on 19-Jun-16.
 */
public abstract class Item
{
    protected String name;

    protected String getName()
    {
        return name;
    }

    protected abstract Item clone();
    public abstract void excute(Activity activity);
}
