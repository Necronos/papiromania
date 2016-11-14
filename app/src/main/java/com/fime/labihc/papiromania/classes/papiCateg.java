package com.fime.labihc.papiromania.classes;


import java.util.ArrayList;

public class PapiCateg {
    private String name;
    private int imageResId;
    private ArrayList<PapiItem> items;

    public PapiCateg(String name, int imageResId){
        this.name = name;
        this.imageResId = imageResId;
        this.items = new ArrayList<>();
    }

    public PapiCateg(String name, int imageResId, ArrayList<PapiItem> items){
        this.name = name;
        this.imageResId = imageResId;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public ArrayList<PapiItem> getItems() {
        return items;
    }

    public void addItem(PapiItem item){
        items.add(item);
    }
}
