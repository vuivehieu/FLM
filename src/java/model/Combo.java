package model;

import java.sql.Date;
import java.util.List;

public class Combo {
    private int cid;
    private String tag;
    private String comboName_EN;
    private String comboName_VI;
    private String note;
    private Date createDate;
    private boolean isActive;
    
    
    private List<Subject> subject;
    

    public Combo() {
    }

    public Combo(int cid, String tag, String comboName_EN, String comboName_VI, String note, Date createDate, boolean isActive, List<Subject> subject) {
        this.cid = cid;
        this.tag = tag;
        this.comboName_EN = comboName_EN;
        this.comboName_VI = comboName_VI;
        this.note = note;
        this.createDate = createDate;
        this.isActive = isActive;
        this.subject = subject;
    }

    

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getComboName_EN() {
        return comboName_EN;
    }

    public void setComboName_EN(String comboName_EN) {
        this.comboName_EN = comboName_EN;
    }

    public String getComboName_VI() {
        return comboName_VI;
    }

    public void setComboName_VI(String comboName_VI) {
        this.comboName_VI = comboName_VI;
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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    
    
    
    
    
    
}
