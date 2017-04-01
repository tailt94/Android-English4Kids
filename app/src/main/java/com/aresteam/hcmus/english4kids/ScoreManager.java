package com.aresteam.hcmus.english4kids;

import android.content.Context;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by Wolf on 24-Jun-16.
 */
public class ScoreManager {
    public static void SaveScore (Context context, int index, int score){
        String indexToString = String.valueOf(index);
        String scoreToString = String.valueOf(score);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(indexToString, scoreToString).commit();
    }
    public static int LoadScore(Context context, int keyword) {
        String keywordToString = String.valueOf(keyword);
        String result1 = PreferenceManager.getDefaultSharedPreferences(context).getString(keywordToString, "1000");
        return Integer.parseInt(result1);
    }
}
