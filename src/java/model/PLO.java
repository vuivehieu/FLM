/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phanh
 */
public class PLO {
    private int ploid;
    private String ploName;
    private String ploDescription;
    private boolean isActive;

    public PLO() {
    }

    public PLO(int ploid, String ploName, String ploDescription, boolean isActive) {
        this.ploid = ploid;
        this.ploName = ploName;
        this.ploDescription = ploDescription;
        this.isActive = isActive;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getPloid() {
        return ploid;
    }

    public void setPloid(int ploid) {
        this.ploid = ploid;
    }

    public String getPloName() {
        return ploName;
    }

    public void setPloName(String ploName) {
        this.ploName = ploName;
    }

    public String getPloDescription() {
        return ploDescription;
    }

    public void setPloDescription(String ploDescription) {
        this.ploDescription = ploDescription;
    }
    
    
}
