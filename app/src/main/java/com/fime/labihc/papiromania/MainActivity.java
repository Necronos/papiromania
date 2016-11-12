package com.fime.labihc.papiromania;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] categorias = {"Animales", "Figuras Geométricas", "Plantas", "Otros"};
        int[] categoFotos = {R.drawable.catego_animals, R.drawable.catego_geometry, R.drawable.catego_flowers, R.drawable.catego_others};

        //ListAdapter adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categorias);
        //ListView listCategories = (ListView) findViewById(R.id.list_categories);
        CustomAdapter adaptador = new CustomAdapter(this, categorias, categoFotos);
        ListView listCategories = (ListView) findViewById(R.id.list_categories);
        listCategories.setAdapter(adaptador);

        AdapterView.OnItemClickListener mMessageClickedHandled = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String categoria = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(MainActivity.this, categoria, Toast.LENGTH_SHORT).show();

            }
        };
        listCategories.setOnItemClickListener(mMessageClickedHandled);


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
}