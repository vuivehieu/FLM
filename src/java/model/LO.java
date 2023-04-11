/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phanh
 */
public class LO {
    private int loid;
    private int slbid;
    private String cloName;
    private String cloDetail;
    private String loDetail;
    private boolean isActive;

    public LO() {
    }

    public LO(int loid, int slbid, String cloName, String cloDetail, String loDetail, boolean isActive) {
        this.loid = loid;
        this.slbid = slbid;
        this.cloName = cloName;
        this.cloDetail = cloDetail;
        this.loDetail = loDetail;
        this.isActive = isActive;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    

    public int getLoid() {
        return loid;
    }

    public void setLoid(int loid) {
        this.loid = loid;
    }

    public int getSlbid() {
        return slbid;
    }

    public void setSlbid(int slbid) {
        this.slbid = slbid;
    }

    public String getCloName() {
        return cloName;
    }

    public void setCloName(String cloName) {
        this.cloName = cloName;
    }

    public String getCloDetail() {
        return cloDetail;
    }

    public void setCloDetail(String cloDetail) {
        this.cloDetail = cloDetail;
    }

    public String getLoDetail() {
        return loDetail;
    }

    public void setLoDetail(String loDetail) {
        this.loDetail = loDetail;
    }
    
    
    
}
