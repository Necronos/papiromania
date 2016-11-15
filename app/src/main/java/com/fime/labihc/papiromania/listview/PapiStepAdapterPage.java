package com.fime.labihc.papiromania.listview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fime.labihc.papiromania.R;
import com.fime.labihc.papiromania.classes.PapiStep;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PapiStepAdapterPage extends PagerAdapter   {
    private Context context;
    private int layoutResId;
    private ArrayList<PapiStep> data;


    public PapiStepAdapterPage(Context context, int layoutResId, ArrayList<PapiStep> data) {
        this.context = context;
        this.layoutResId = layoutResId;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position){
        LayoutInflater listInflayer = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) listInflayer.inflate(layoutResId,collection,false);
        PapiStep papiStep = data.get(position);

        TextView descrption = (TextView) layout.findViewById(R.id.stepsText);
        ImageView picture = (ImageView) layout.findViewById(R.id.stepsImage);

        descrption.setText(papiStep.getDescription());
        picture.setImageResource(papiStep.getImgResId());

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return data.get(position).getTitle();
    }
}
