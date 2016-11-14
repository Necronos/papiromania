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
import com.fime.labihc.papiromania.classes.PapiCateg;

import java.util.ArrayList;

public class PapiAdapter extends ArrayAdapter<PapiCateg>{
    private int layoutResId;

    public PapiAdapter(Context context, int layoutResId, ArrayList<PapiCateg> items){
        super(context,layoutResId,items);
        this.layoutResId = layoutResId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater listInflayer = LayoutInflater.from(getContext());
        View customView = listInflayer.inflate(layoutResId, parent, false);
        TextView categText = (TextView) customView.findViewById(R.id.categText);
        ImageView categImage = (ImageView) customView.findViewById(R.id.categImage);

        PapiCateg papiCateg = getItem(position);

        categText.setText(papiCateg.getName());
        categImage.setImageResource(papiCateg.getImageResId());

        return customView;
    }
}
