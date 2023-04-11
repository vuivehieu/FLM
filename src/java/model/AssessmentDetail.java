/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;

public class AssessmentDetail {

    private int assdeID;
    private int assID;
    private String assessmentItem;
    private float weight;
    private boolean isMain;

    public AssessmentDetail() {
    }

    public AssessmentDetail(int assdeID, int assID, String assessmentItem, float weight, boolean isMain) {
        this.assdeID = assdeID;
        this.assID = assID;
        this.assessmentItem = assessmentItem;
        this.weight = weight;
        this.isMain = isMain;
    }

    public boolean isIsMain() {
        return isMain;
    }

    public void setIsMain(boolean isMain) {
        this.isMain = isMain;
    }

    public int getAssdeID() {
        return assdeID;
    }

    public void setAssdeID(int assdeID) {
        this.assdeID = assdeID;
    }

    public int getAssID() {
        return assID;
    }

    public void setAssID(int assID) {
        this.assID = assID;
    }

    public String getAssessmentItem() {
        return assessmentItem;
    }

    public void setAssessmentItem(String assessmentItem) {
        this.assessmentItem = assessmentItem;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getWeightFormat() {
        String patternPercent = "###.#%";
        DecimalFormat f = new DecimalFormat(patternPercent);
        return f.format(weight);
    }

}
