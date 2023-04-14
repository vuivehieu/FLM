/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author phanh
 */
public class Curriculum {
    private int curid;
    private String curCode;
    private String curName_EN;
    private String curName_VI;
    private String description;
    private boolean isApproved;
    private boolean isActive;
    private String image;
    
    private Major major;
    private Decision decision;
    
    private List<Subject> subject;
    private List<PLO> plo;
    private List<PO> po;
    private List<Combo> combo;
    private List<Elective> elective;
    
    

    public Curriculum() {
    }

    public Curriculum(int curid, String curCode, String curName_EN, String curName_VI, String description, boolean isApproved, boolean isActive, String image, Major major, Decision decision, List<Subject> subject, List<PLO> plo, List<PO> po, List<Combo> combo, List<Elective> elective) {
        this.curid = curid;
        this.curCode = curCode;
        this.curName_EN = curName_EN;
        this.curName_VI = curName_VI;
        this.description = description;
        this.isApproved = isApproved;
        this.isActive = isActive;
        this.image = image;
        this.major = major;
        this.decision = decision;
        this.subject = subject;
        this.plo = plo;
        this.po = po;
        this.combo = combo;
        this.elective = elective;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }


    public int getCurid() {
        return curid;
    }

    public void setCurid(int curid) {
        this.curid = curid;
    }

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public String getCurName_EN() {
        return curName_EN;
    }

    public void setCurName_EN(String curName_EN) {
        this.curName_EN = curName_EN;
    }

    public String getCurName_VI() {
        return curName_VI;
    }

    public void setCurName_VI(String curName_VI) {
        this.curName_VI = curName_VI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public List<Combo> getCombo() {
        return combo;
    }

    public void setCombo(List<Combo> combo) {
        this.combo = combo;
    }

    public List<Elective> getElective() {
        return elective;
    }

    public void setElective(List<Elective> elective) {
        this.elective = elective;
    }

    public List<PO> getPo() {
        return po;
    }

    public void setPo(List<PO> po) {
        this.po = po;
    }

    public List<PLO> getPlo() {
        return plo;
    }

    public void setPlo(List<PLO> plo) {
        this.plo = plo;
    }
    
    public int getTotalCredit(){
        int total = 0;
        for (Subject i : subject) {
            total += i.getNoCredit();
        }
        return total;
    }

}
