package model;

import java.sql.Date;
import java.util.List;

public class Elective {
    private int eid;
    private String ename;
    private String tag;
    private String subjectName_EN;
    private String subjectName_VI;
    private String note;
    private Date createDate;
    private boolean isActive;
    
    private List<Subject> subject;
    
    public Elective() {
    }

    public Elective(int eid, String ename, String tag, String subjectName_EN, String subjectName_VI, String note, Date createDate, boolean isActive, List<Subject> subject) {
        this.eid = eid;
        this.ename = ename;
        this.tag = tag;
        this.subjectName_EN = subjectName_EN;
        this.subjectName_VI = subjectName_VI;
        this.note = note;
        this.createDate = createDate;
        this.isActive = isActive;
        this.subject = subject;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    
    

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
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
    
    
}
