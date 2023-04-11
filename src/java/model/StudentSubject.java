package model;

import java.sql.Date;

public class StudentSubject {
    private String stid;
    private String subjectCode;
    private int isLearning;
    private Date startDate;
    private Date endDate;
    private String status;

    public StudentSubject() {
    }

    public StudentSubject(String stid, String subjectCode, int isLearning, Date startDate, Date endDate, String status) {
        this.stid = stid;
        this.subjectCode = subjectCode;
        this.isLearning = isLearning;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getIsLearning() {
        return isLearning;
    }

    public void setIsLearning(int isLearning) {
        this.isLearning = isLearning;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    

}
