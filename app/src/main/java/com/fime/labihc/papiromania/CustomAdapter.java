package com.fime.labihc.papiromania;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String>{
    int[] imgs;
    public CustomAdapter(Context context, String[] categories, int[] categoFotos) {
        super(context, R.layout.custom_list, categories);
        this.imgs = categoFotos;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater listInflayer = LayoutInflater.from(getContext());
        View customView = listInflayer.inflate(R.layout.custom_list, parent, false);

        String singleCategorie = getItem(position);
        TextView categText = (TextView) customView.findViewById(R.id.categText);
        ImageView categImage = (ImageView) customView.findViewById(R.id.categImage);

        categText.setText(singleCategorie);
        categImage.setImageResource(imgs[position]);
        return customView;
    }
}
