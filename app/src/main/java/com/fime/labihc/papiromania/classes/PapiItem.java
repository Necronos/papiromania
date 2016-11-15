package com.fime.labihc.papiromania.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class PapiItem
        implements Serializable{ // para que se pueda enviar entre activities

    private String name;                // Name of the papi item
    private int imageResourceID;        // Set of images
    private ArrayList<PapiStep> steps;  // Array of steps to make it possible
    private int actualStep;             // Pointer to the current step (step 0 must always exists)

    public PapiItem(int imageResourceID){
        this.name = "misingNo";
        this.steps = new ArrayList<>();
        this.actualStep = 0;
        this.imageResourceID = imageResourceID;
    }

    public PapiItem(int imageResourceID,String name){
        this.name = name;
        this.steps = new ArrayList<>();
        this.actualStep = 0;
        this.imageResourceID = imageResourceID;
    }

    public PapiItem(int imageResourceID,String name, ArrayList<PapiStep> steps){
        this.name = name;
        this.steps = steps;
        this.actualStep = 0;
        this.imageResourceID = imageResourceID;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    /**
     * Resets the PapiItem to the first step
     */
    public void initialize(){
        actualStep = 0;
    }

    /***
     * Add an step for this PapiItem in a specific location
     * @param title Title of the step
     * @param description Description or the step to follow
     * @param imgResId Image localization
     */
    public void addStepAt(String title, String description, int imgResId, int index){
        if(index >= steps.size()){
            addStep(title, description,imgResId);
            return;
        }

        if(index < 0){
            return;
        }

        steps.add(index,new PapiStep(index,title,description,imgResId));

        for (int i = index + 1; i < steps.size(); i++){
            steps.get(i).setSequenceNumber(i);
        }
    }

    /***
     * Add an step for this PapiItem
     * @param title Title of the step
     * @param description Description or the step to follow
     * @param imgResId Image localization
     */
    public void addStep(String title, String description, int imgResId){
        steps.add(steps.size(),new PapiStep(steps.size(),title,description,imgResId));
    }

    /***
     * Function that indicates if this papiItem can step back
     * @return true if can go back, otherwise false
     */
    boolean canGoBack(){
        return this.actualStep > 0;
    }

    /***
     * Function that indicates if this papiItem can step forward
     * @return true if can go next, otherwise false
     */
    boolean canGoNext(){
        return this.actualStep <  this.steps.size();
    }

    /***
     * Move a step backward
     * @return If can, returns the previus PapiStep, otherwise return null
     */
    PapiStep goBack(){
        if (!canGoBack()){
            return null;
        }
        return this.steps.get(--actualStep);
    }

    /***
     * Move a step forward
     * @return If can, returns the next PapiStep, otherwise return null
     */
    PapiStep goNext(){
        if(!canGoNext()){
            return null;
        }
        return this.steps.get(++actualStep);
    }
}
