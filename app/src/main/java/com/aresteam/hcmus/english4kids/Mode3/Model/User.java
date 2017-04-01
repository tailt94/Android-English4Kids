package com.aresteam.hcmus.english4kids.Mode3.Model;

import android.app.Activity;

/**
 * Created by trann on 22-Jun-16.
 */

public class User
{
    private int scores;
    private ItemManager itemManager;

    public User()
    {
        scores = 0;
        itemManager = new ItemManager();
    }


    public int getScores()
    {
        return scores;
    }

    public void setScores(int s)
    {
        scores = s;
    }

    public void receiveWinScores(int WIN_SCORE)
    {
        scores = scores + WIN_SCORE;
    }

    public void receiveItem(Item item)
    {
        itemManager.add(item);
    }

    public int countItemByName(String name)
    {
        return itemManager.countItemByName(name);
    }

    public void useItem(String name, Activity activity)
    {
        Item item = itemManager.getItemByName(name);

        item.excute(activity);

        itemManager.remove(item);
    }
}
