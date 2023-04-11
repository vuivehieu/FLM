package model;

import java.util.List;


public class Session {
    private int sesid;
    private int slbid;
    private int sesNo;
    private String topic;
    private String learningTeachingType;
    private String ITU;
    private String  studentMaterial;
    private String dowload;
    private String studentTask;
    private String urls;
    private String note;
    private boolean isActive;
    private int accountID;
    
    private List<Question> question;

    public Session() {
    }

    public Session(int sesid, int slbid, int sesNo, String topic, String learningTeachingType, String ITU, String studentMaterial, String dowload, String studentTask, String urls, String note, boolean isActive, int accountID, List<Question> question) {
        this.sesid = sesid;
        this.slbid = slbid;
        this.sesNo = sesNo;
        this.topic = topic;
        this.learningTeachingType = learningTeachingType;
        this.ITU = ITU;
        this.studentMaterial = studentMaterial;
        this.dowload = dowload;
        this.studentTask = studentTask;
        this.urls = urls;
        this.note = note;
        this.isActive = isActive;
        this.accountID = accountID;
        this.question = question;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    

    

    public int getSesid() {
        return sesid;
    }

    public void setSesid(int sesid) {
        this.sesid = sesid;
    }

    public int getSlbid() {
        return slbid;
    }

    public void setSlbid(int slbid) {
        this.slbid = slbid;
    }

    

    public int getSesNo() {
        return sesNo;
    }

    public void setSesNo(int sesNo) {
        this.sesNo = sesNo;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLearningTeachingType() {
        return learningTeachingType;
    }

    public void setLearningTeachingType(String learningTeachingType) {
        this.learningTeachingType = learningTeachingType;
    }

    public String getITU() {
        return ITU;
    }

    public void setITU(String ITU) {
        this.ITU = ITU;
    }

    public String getStudentMaterial() {
        return studentMaterial;
    }

    public void setStudentMaterial(String studentMaterial) {
        this.studentMaterial = studentMaterial;
    }

    public String getDowload() {
        return dowload;
    }

    public void setDowload(String dowload) {
        this.dowload = dowload;
    }

    public String getStudentTask() {
        return studentTask;
    }

    public void setStudentTask(String studentTask) {
        this.studentTask = studentTask;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }
    
    
}
