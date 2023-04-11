
package model;

import java.sql.Date;

public class Student extends Account{
    private String stid;
    private String semester;
    private boolean isActive;

    private Curriculum curriculum;
    public Student() {
    }

    public Student(int accountID, String userName, String password, String displayName, String email, String avatar, boolean isBlock, int status, Date createDate, Role role, String stid, String semester, boolean isActive, Curriculum curriculum) {
        super(accountID, userName, password, displayName, email, avatar, isBlock, status, createDate, role);
        this.stid = stid;
        this.semester = semester;
        this.isActive = isActive;
        this.curriculum = curriculum;
    }

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
    
    
}
