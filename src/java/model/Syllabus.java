package model;

import java.sql.Date;
import java.util.List;

public class Syllabus {
    private int slbid;
    private String subjectCode;
    private String slbName_EN;
    private String slbName_VI;
    private String degreeLevel;
    private String timeAllocation;
    private String description;
    private String studentTask;
    private String tool;
    private boolean isApproved;
    private boolean isActive;
    private Date approvedDate;
    private String note;
    private int minAvgMarkToPass;
    
    private Decision decision;
    private List<Material> material;
    private List<Session> session;
    private List<Assessment> assessment;
    private List<PreRequisite> preReq;
    private List<LO> lo;
    private int accountID;
    
    public Syllabus() {
    }

    public Syllabus(int slbid, String subjectCode, String slbName_EN, String slbName_VI, String degreeLevel, String timeAllocation, String description, String studentTask, String tool, boolean isApproved, boolean isActive, Date approvedDate, String note, int minAvgMarkToPass, Decision decision, List<Material> material, List<Session> session, List<Assessment> assessment, List<PreRequisite> preReq, List<LO> lo, int accountID) {
        this.slbid = slbid;
        this.subjectCode = subjectCode;
        this.slbName_EN = slbName_EN;
        this.slbName_VI = slbName_VI;
        this.degreeLevel = degreeLevel;
        this.timeAllocation = timeAllocation;
        this.description = description;
        this.studentTask = studentTask;
        this.tool = tool;
        this.isApproved = isApproved;
        this.isActive = isActive;
        this.approvedDate = approvedDate;
        this.note = note;
        this.minAvgMarkToPass = minAvgMarkToPass;
        this.decision = decision;
        this.material = material;
        this.session = session;
        this.assessment = assessment;
        this.preReq = preReq;
        this.lo = lo;
        this.accountID = accountID;
    }

    

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public List<LO> getLo() {
        return lo;
    }

    public void setLo(List<LO> lo) {
        this.lo = lo;
    }

    public List<PreRequisite> getPreReq() {
        return preReq;
    }

    public void setPreReq(List<PreRequisite> preReq) {
        this.preReq = preReq;
    }

    public int getSlbid() {
        return slbid;
    }

    public void setSlbid(int slbid) {
        this.slbid = slbid;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSlbName_EN() {
        return slbName_EN;
    }

    public void setSlbName_EN(String slbName_EN) {
        this.slbName_EN = slbName_EN;
    }

    public String getSlbName_VI() {
        return slbName_VI;
    }

    public void setSlbName_VI(String slbName_VI) {
        this.slbName_VI = slbName_VI;
    }

    public String getDegreeLevel() {
        return degreeLevel;
    }

    public void setDegreeLevel(String degreeLevel) {
        this.degreeLevel = degreeLevel;
    }

    public String getTimeAllocation() {
        return timeAllocation;
    }

    public void setTimeAllocation(String timeAllocation) {
        this.timeAllocation = timeAllocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudentTask() {
        return studentTask;
    }

    public void setStudentTask(String studentTask) {
        this.studentTask = studentTask;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMinAvgMarkToPass() {
        return minAvgMarkToPass;
    }

    public void setMinAvgMarkToPass(int minAvgMarkToPass) {
        this.minAvgMarkToPass = minAvgMarkToPass;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public List<Material> getMaterial() {
        return material;
    }

    public void setMaterial(List<Material> material) {
        this.material = material;
    }

    public List<Session> getSession() {
        return session;
    }

    public void setSession(List<Session> session) {
        this.session = session;
    }

    public List<Assessment> getAssessment() {
        return assessment;
    }

    public void setAssessment(List<Assessment> assessment) {
        this.assessment = assessment;
    }

    
    
}
