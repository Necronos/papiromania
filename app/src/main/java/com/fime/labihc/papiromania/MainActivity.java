package com.fime.labihc.papiromania;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fime.labihc.papiromania.API.DataManager;
import com.fime.labihc.papiromania.classes.PapiCateg;
import com.fime.labihc.papiromania.listview.PapiAdapter;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener { // ListView Clicks are handled by this activity

    private Toast toast;
    private long lastBackPressTime = 0;

    public String loadJSONFromAsset() {
        String json = null;
        try {
            AssetManager assetManager = getBaseContext().getAssets();
            InputStream is = assetManager.open("categories.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find elements
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ListView listCategories = (ListView) findViewById(R.id.list_categories);

        setSupportActionBar(toolbar);

        // Setting data
        String jsonstr = loadJSONFromAsset();

        DataManager.reloadCategories(jsonstr);

        ArrayList<PapiCateg> categories = DataManager.getCategoriesAsArray();
        PapiAdapter papiAdapter = new PapiAdapter(this,R.layout.custom_list,categories);

        listCategories.setAdapter(papiAdapter);

        listCategories.setOnItemClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_instructions:
                startActivity(new Intent(this, Instructions.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getBaseContext(),CategoryActivity.class);
        intent.putExtra(DataManager.EXTRA_CATEGORY,((PapiCateg)adapterView.getItemAtPosition(i)));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Presiona de nuevo para cerrar la app", 4000);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }
}