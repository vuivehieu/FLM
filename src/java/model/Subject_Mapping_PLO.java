/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phanh
 */
public class Subject_Mapping_PLO {
    private String subjectCode;
    private int ploid;
    private boolean isMapping;

    public Subject_Mapping_PLO() {
    }

    public Subject_Mapping_PLO(String subjectCode, int ploid, boolean isMapping) {
        this.subjectCode = subjectCode;
        this.ploid = ploid;
        this.isMapping = isMapping;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getPloid() {
        return ploid;
    }

    public void setPloid(int ploid) {
        this.ploid = ploid;
    }

    public boolean isIsMapping() {
        return isMapping;
    }

    public void setIsMapping(boolean isMapping) {
        this.isMapping = isMapping;
    }
    
    
}
