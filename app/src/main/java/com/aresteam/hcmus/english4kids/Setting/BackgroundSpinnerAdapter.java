package com.aresteam.hcmus.english4kids.Setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.aresteam.hcmus.english4kids.R;

import java.util.List;

/**
 * Created by User on 06/19/16.
 */
public class BackgroundSpinnerAdapter extends ArrayAdapter<Background> {
    private Context context;

    public BackgroundSpinnerAdapter(Context _context, int _resource, List<Background> _objects) {
        super(_context, _resource, _objects);
        context = _context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position);
    }

    public View getCustomView(int position) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = layoutInflater.inflate(R.layout.custom_setting_spinner, null);
        Background background = (Background)getItem(position);

        ImageView imageViewBackground = (ImageView)row.findViewById(R.id.imageViewBackground);
        imageViewBackground.setImageResource(background.getImg());

        return row;
    }
}
