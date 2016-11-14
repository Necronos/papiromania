package com.fime.labihc.papiromania.classes;

/**
 * Created by jd-14 on 11/13/2016.
 */

public class PapiStep {
    private int sequenceNumber;
    private String title;
    private String description;
    private int imgResId;

    public PapiStep(int sequenceNumber, String title, String description, int imgResId) {
        this.sequenceNumber = sequenceNumber;
        this.title = title;
        this.description = description;
        this.imgResId = imgResId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
