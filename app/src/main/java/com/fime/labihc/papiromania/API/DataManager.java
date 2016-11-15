package com.fime.labihc.papiromania.API;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fime.labihc.papiromania.R;
import com.fime.labihc.papiromania.RandomGenerator;
import com.fime.labihc.papiromania.classes.PapiCateg;
import com.fime.labihc.papiromania.classes.PapiItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;


public class DataManager {
    private static DataManager instance = new DataManager(); // singleton
    private HashMap<String,PapiCateg> categories;

    public static String EXTRA_CATEGORY = "EXTRA_CATEGORY2016";


    private DataManager(){ // in private mode, it cannot be instantiated
        categories = new HashMap<>();
    }

    public static void reloadCategories(String jsonStr){
        instance.categories.clear(); // Delete existing categories

        // Building categories
        if (jsonStr != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);

                JSONArray categorias = jsonObject.getJSONArray("categorias");

                for (int i = 0; i < categorias.length(); i++){
                    JSONObject c = categorias.getJSONObject(i);
                    String name = c.getString("name");
                    int imgId = c.getInt("imgId");
                    instance.categories.put(name, new PapiCateg(name,imgId));

                    JSONArray papite = c.getJSONArray("PapiItems");
                    for (int y = 0; y < papite.length(); y++){
                        JSONObject f = papite.getJSONObject(y);
                        name = f.getString("name");
                        imgId = f.getInt("imgId");
                        PapiItem papiItem = new PapiItem(imgId, name);

                        if (y==0) {
                            JSONArray papist = f.getJSONArray("PapiSteps");
                            for (int z = 0; z < papist.length(); z++){
                                JSONObject s = papist.getJSONObject(z);
                                String title = s.getString("title");
                                imgId = s.getInt("imgId");
                                String description = s.getString("description");
                                papiItem.addStep(title,description,imgId);
                            }
                        }
                    }
                }

            } catch (final JSONException e){
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        }
    }

    public static ArrayList<PapiCateg> getCategoriesAsArray() {
        return  new ArrayList<PapiCateg>(instance.categories.values());
    }


}
