/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phanh
 */
public class PO_Mapping_PLO {
    private int curid;
    private int ploid;
    private int poid;
    private boolean isMapping;

    public PO_Mapping_PLO() {
    }

    public PO_Mapping_PLO(int curid, int ploid, int poid, boolean isMapping) {
        this.curid = curid;
        this.ploid = ploid;
        this.poid = poid;
        this.isMapping = isMapping;
    }

    public int getCurid() {
        return curid;
    }

    public void setCurid(int curid) {
        this.curid = curid;
    }

    public int getPloid() {
        return ploid;
    }

    public void setPloid(int ploid) {
        this.ploid = ploid;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public boolean isIsMapping() {
        return isMapping;
    }

    public void setIsMapping(boolean isMapping) {
        this.isMapping = isMapping;
    }
    
    
}
