package com.fime.labihc.papiromania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.fime.labihc.papiromania.API.DataManager;
import com.fime.labihc.papiromania.classes.PapiCateg;
import com.fime.labihc.papiromania.listview.PapiItemAdapter;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Find elements
        ListView lvItems = (ListView) findViewById(R.id.listview_items);

        // Gattering data
        PapiCateg papiCateg = (PapiCateg)getIntent().getSerializableExtra(DataManager.EXTRA_CATEGORY);

        PapiItemAdapter papiItemAdapter = new PapiItemAdapter(this, R.layout.custom_list, papiCateg.getItems());

        lvItems.setAdapter(papiItemAdapter);
    }
}
