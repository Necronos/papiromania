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
import com.fime.labihc.papiromania.classes.PapiStep;

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

    public static final int RESULT_CATEG = 12312;
    private static DataManager instance = new DataManager(); // singleton
    private HashMap<String,PapiCateg> categories;

    public static final String EXTRA_CATEGORY = "EXTRA_CATEGORY2016";
    public static final String EXTRA_ITEM = "EXTRA_ITEM2016";

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
                    PapiCateg papiCateg = new PapiCateg(name, ImgResParser.mapImgId(imgId));
                    instance.categories.put(name, papiCateg);

                    JSONArray papite = c.getJSONArray("PapiItems");
                    for (int y = 0; y < papite.length(); y++){
                        JSONObject f = papite.getJSONObject(y);
                        name = f.getString("name");
                        imgId = f.getInt("imgId");
                        PapiItem papiItem = new PapiItem( ImgResParser.mapImgId(imgId), name);

                        // Obtenemos los pasos
                        JSONArray papiSteps = f.getJSONArray("PapiSteps");

                        for(int step = 0; step < papiSteps.length(); step++){
                            //int sequenceNumber, String title, String description, int imgResId
                            JSONObject papiStepJson = papiSteps.getJSONObject(step);
                            papiItem.addStep(papiStepJson.getString("title"),
                                    papiStepJson.getString("description"),
                                    ImgResParser.mapImgId(papiStepJson.getInt("imgId")));
                        }
                        papiCateg.addItem(papiItem);
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
