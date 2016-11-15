package com.fime.labihc.papiromania;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PapiStepAdapterPage(this,R.layout.activity_figure_steps,papiItem.getSteps()));

        Button next = (Button) this.findViewById(R.id.nextButton);
        Button previous = (Button) this.findViewById(R.id.backButton);

        next.setOnClickListener(new View.OnClickListener() {
            private int getItem(int i) {
                return viewPager.getCurrentItem() + i;
            }
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(getItem(+1), true);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            private int getItem(int i) {
                return viewPager.getCurrentItem() + i;
            }
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(getItem(-1), true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(DataManager.EXTRA_CATEGORY, papiCateg);
        setResult(1, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_steps, menu);
        return true;
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
        if (menuItem.getItemId() == R.id.action_home){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }

        return true;//super.onOptionsItemSelected(menuItem);
    }


}
