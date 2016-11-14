package com.fime.labihc.papiromania.API;

import com.fime.labihc.papiromania.R;
import com.fime.labihc.papiromania.RandomGenerator;
import com.fime.labihc.papiromania.classes.PapiCateg;
import com.fime.labihc.papiromania.classes.PapiItem;

import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    private static DataManager instance = new DataManager(); // singleton
    private HashMap<String,PapiCateg> categories;

    public static String EXTRA_CATEGORY = "EXTRA_CATEGORY2016";


    private DataManager(){ // in private mode, it cannot be instantiated
        categories = new HashMap<>();
        reloadCategories();
    }

    public void reloadCategories(){
        categories.clear(); // Delete existing categories

        // the following can change and it is for testing purposes only

        categories.put("Animales"           ,new PapiCateg("Animales"           , R.drawable.catego_animals));
        categories.put("Figuras Geométricas",new PapiCateg("Figuras Geométricas", R.drawable.catego_geometry));
        categories.put("Plantas"            ,new PapiCateg("Plantas"            , R.drawable.catego_flowers));
        categories.put("Otros"              ,new PapiCateg("Otros"              , R.drawable.catego_others));

        // Building papiItems
        for(int i = 0; i< 20; i++){
            PapiItem papiItem = new PapiItem(R.drawable.catego_animals,RandomGenerator.getRandomString(10));
            for(int j = 0; j < 4; j++){
                papiItem.addStep(RandomGenerator.getRandomString(5),RandomGenerator.getRandomString(20),R.drawable.catego_geometry);
            }
            switch (i % 4){
                case 0:
                    categories.get("Animales").addItem(papiItem);
                    break;
                case 1:
                    categories.get("Figuras Geométricas").addItem(papiItem);
                    break;
                case 2:
                    categories.get("Plantas").addItem(papiItem);
                    break;
                case 3:
                    categories.get("Otros").addItem(papiItem);
                    break;
            }
        }
    }

    public static ArrayList<PapiCateg> getCategoriesAsArray() {
        return  new ArrayList<PapiCateg>(instance.categories.values());
    }

}
