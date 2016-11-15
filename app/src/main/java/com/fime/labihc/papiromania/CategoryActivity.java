package com.fime.labihc.papiromania;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fime.labihc.papiromania.API.DataManager;
import com.fime.labihc.papiromania.classes.PapiCateg;
import com.fime.labihc.papiromania.classes.PapiItem;
import com.fime.labihc.papiromania.listview.PapiItemAdapter;

public class CategoryActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener{
    private PapiCateg papiCateg;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Find elements
        lvItems = (ListView) findViewById(R.id.listview_items);

        // Gattering data
        papiCateg = (PapiCateg)getIntent().getSerializableExtra(DataManager.EXTRA_CATEGORY);
        loadStuff(papiCateg);
    }

    private void loadStuff(PapiCateg papiCateg){
        if(papiCateg != null) {
            PapiItemAdapter papiItemAdapter = new PapiItemAdapter(this, R.layout.custom_list, papiCateg.getItems());
            lvItems.setAdapter(papiItemAdapter);
            lvItems.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getBaseContext(),PapiStepPageViewerActivity.class);
        intent.putExtra(DataManager.EXTRA_ITEM,((PapiItem)adapterView.getItemAtPosition(i)));
        intent.putExtra(DataManager.EXTRA_CATEGORY,papiCateg);
        startActivityForResult(intent,DataManager.RESULT_CATEG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DataManager.RESULT_CATEG) {
            papiCateg = (PapiCateg) data.getSerializableExtra(DataManager.EXTRA_CATEGORY);
            loadStuff(papiCateg);
        }
    }
}
