/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author phanh
 */
public class Subject {

    private String subjectCode;
    private String subjectName_EN;
    private String subjectName_VI;
    private String semester;
    private int noCredit;
    private String note;
    private Date createDate;
    private boolean isCombo;
    private String comboName;
    private boolean isElective;
    private String electiveName;
    private boolean isActive;
    private List<Syllabus> syllabus;

    public Subject() {
    }

    public Subject(String subjectCode, String subjectName_EN, String subjectName_VI, String semester, int noCredit, String note, Date createDate, boolean isCombo, String comboName, boolean isElective, String electiveName, Boolean isActive, List<Syllabus> syllabus) {
        this.subjectCode = subjectCode;
        this.subjectName_EN = subjectName_EN;
        this.subjectName_VI = subjectName_VI;
        this.semester = semester;
        this.noCredit = noCredit;
        this.note = note;
        this.createDate = createDate;
        this.isCombo = isCombo;
        this.comboName = comboName;
        this.isElective = isElective;
        this.electiveName = electiveName;
        this.isActive = isActive;
        this.syllabus = syllabus;
    }

    

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    

    public String getElectiveName() {
        return electiveName;
    }

    public void setElectiveName(String electiveName) {
        this.electiveName = electiveName;
    }

    public boolean isIsElective() {
        return isElective;
    }

    public void setIsElective(boolean isElective) {
        this.isElective = isElective;
    }

    public boolean isIsCombo() {
        return isCombo;
    }

    public void setIsCombo(boolean isCombo) {
        this.isCombo = isCombo;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName_EN() {
        return subjectName_EN;
    }

    public void setSubjectName_EN(String subjectName_EN) {
        this.subjectName_EN = subjectName_EN;
    }

    public String getSubjectName_VI() {
        return subjectName_VI;
    }

    public void setSubjectName_VI(String subjectName_VI) {
        this.subjectName_VI = subjectName_VI;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getNoCredit() {
        return noCredit;
    }

    public void setNoCredit(int noCredit) {
        this.noCredit = noCredit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public List<Syllabus> getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(List<Syllabus> syllabus) {
        this.syllabus = syllabus;
    }
}
