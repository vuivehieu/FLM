/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phanh
 */
public class Grade {

    private int assdeID;
    private String stid;
    private float value;
    private String comment;

    public Grade() {
    }

    public Grade(int assdeID, String stid, float value, String comment) {
        this.assdeID = assdeID;
        this.stid = stid;
        this.value = value;
        this.comment = comment;
    }

    public int getAssdeID() {
        return assdeID;
    }

    public void setAssdeID(int assdeID) {
        this.assdeID = assdeID;
    }

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    

}
