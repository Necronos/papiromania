package com.fime.labihc.papiromania.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fime.labihc.papiromania.R;
import com.fime.labihc.papiromania.classes.PapiItem;

import java.util.ArrayList;

public class PapiItemAdapter extends ArrayAdapter<PapiItem>{
    private int layoutResId;


    public PapiItemAdapter(Context context, int layoutResId, ArrayList<PapiItem> data) {
        super(context, layoutResId, data);
        this.layoutResId = layoutResId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater listInflayer = LayoutInflater.from(getContext());
        View customView = listInflayer.inflate(layoutResId, parent, false);

        TextView categText = (TextView) customView.findViewById(R.id.categText);
        ImageView categImage = (ImageView) customView.findViewById(R.id.categImage);

        PapiItem papiItem = getItem(position);

        categText.setText(papiItem.getName());
        categImage.setImageResource(papiItem.getImageResourceID());

        return customView;
    }
}
