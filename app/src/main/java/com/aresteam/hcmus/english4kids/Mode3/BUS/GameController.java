package com.aresteam.hcmus.english4kids.Mode3.BUS;

import android.view.View;

import com.aresteam.hcmus.english4kids.Mode3.Model.Letter;
import com.aresteam.hcmus.english4kids.Mode3Activity;


/**
 * Created by trann on 01-Jun-16.
 */
public class GameController
{
    private static GameController instance;

    //Kiem tra cac o trong duoc dien chu cai chua
    private  boolean [] checkList = null;
    //Luu danh sach ID cua EmptyBox
    private  int [] listIDEmtpyBox = null;
    //Luu danh sach cac ID cua Letter
    private int [] listIDLetter = null;
    //Luu danh sach cac ID cua leterr trong EmptyBox
    private  int [] refIDEmptyBox = null;
    //Cau tra loi
    private  String answer = "";
    //
    private int nKyTuTraLoi;

    public String getAnswer()
    {
        return answer;
    }

    public int [] getListIDEmtpyBox()
    {
        return listIDEmtpyBox;
    }

    public int [] getListIDLetter()
    {
        return listIDLetter;
    }

    //Constructor
    private GameController()
    {
    }

    public static GameController getInstance()
    {
        if(null == instance)
        {
            instance = new GameController();
        }

        return instance;
    }

    public void Init(int n)
    {
        checkList = new boolean[n];
        listIDEmtpyBox = new int[n];
        refIDEmptyBox = new int[n];
        int row = (n / Mode3Activity.NUMBER_LETTER_OF_ROW) + (n % Mode3Activity.NUMBER_LETTER_OF_ROW == 0 ? 0 : 1);
        listIDLetter = new int[row*8];
        nKyTuTraLoi = 0;

        answer = "";
        for(int i = 0; i < n; i++)
        {
            answer += ' ';
        }
    }

    public boolean CheckState(View v)
    {
        int n = checkList.length;
        for (int i = 0; i < n; i++)
        {
            if(checkList[i] == true && (v.getId() == listIDEmtpyBox[i]))
            {
                StringBuilder sb = new StringBuilder(answer);
                sb.setCharAt(i, ' ');
                answer = sb.toString();
                checkList[i] = false;
                nKyTuTraLoi--;

                return true;
            }
        }

        return false;
    }

    public void SaveIDEmptyBoxToList(int index, int id)
    {
        listIDEmtpyBox[index] = id;
    }

    public void SaveState(View v)
    {
        Letter letter = (Letter)v;

        int n = checkList.length;
        for (int i = 0; i < n; i++)
        {
            if(false == checkList[i])
            {
                refIDEmptyBox[i] = v.getId();
                checkList[i] = true;

                StringBuilder sb = new StringBuilder(answer);
                sb.setCharAt(i, letter.getLetter().charAt(0));
                answer = sb.toString();

                nKyTuTraLoi++;
                return;
            }
        }
    }

    public boolean KiemTraThangThua(String name)
    {
        return name.equals(answer);
    }

    public int FindIDLetterHidden(int id)
    {
        int n = checkList.length;
        for(int i = 0; i < n; i++)
        {
            if(id == listIDEmtpyBox[i])
            {
                int ret = refIDEmptyBox[i];
                refIDEmptyBox[i] = -1;

                return ret;
            }
        }

        return -1;
    }

    public int FindIDEmptyBox()
    {
        int n = checkList.length;
        for (int i = 0; i < n; i++)
        {
            if(false == checkList[i])
            {
                return listIDEmtpyBox[i];
            }
        }

        return -1;
    }

    public boolean isClick()
    {
        int n = checkList.length;
        for (int i = 0; i < n; i++)
        {
            if(false == checkList[i])
            {
                return true;
            }
        }

        return false;
    }

    public boolean CauTraLoiChuaChinhXac()
    {
        return (answer.length() == nKyTuTraLoi);
    }

    public void SaveIDLetter(int i, int id) {
        listIDLetter[i] = id;
    }
}
