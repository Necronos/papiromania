package com.fime.labihc.papiromania;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.fime.labihc.papiromania.API.DataManager;
import com.fime.labihc.papiromania.classes.PapiCateg;
import com.fime.labihc.papiromania.classes.PapiItem;
import com.fime.labihc.papiromania.listview.PapiStepAdapterPage;

public class PapiStepPageViewerActivity extends AppCompatActivity {
    private PapiCateg papiCateg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papi_step_page_viewer);

        papiCateg = (PapiCateg) getIntent().getSerializableExtra(DataManager.EXTRA_CATEGORY);
        PapiItem papiItem = (PapiItem) getIntent().getSerializableExtra(DataManager.EXTRA_ITEM);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PapiStepAdapterPage(this,R.layout.activity_figure_steps,papiItem.getSteps()));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(DataManager.EXTRA_CATEGORY, papiCateg);
        setResult(1, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            Intent intent = new Intent();
            intent.putExtra(DataManager.EXTRA_CATEGORY, papiCateg);
            setResult(1, intent);
            finish();
            return true;
        }

        return true;//super.onOptionsItemSelected(menuItem);
    }
}
