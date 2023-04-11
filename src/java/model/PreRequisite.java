package model;

public class PreRequisite {
    private int preReqID;
    private int slbid;
    private String subjectCode;
    private String preReqCode;

    public PreRequisite() {
    }

    public PreRequisite(int preReqID, int slbid, String subjectCode, String preReqCode) {
        this.preReqID = preReqID;
        this.slbid = slbid;
        this.subjectCode = subjectCode;
        this.preReqCode = preReqCode;
    }

    public int getSlbid() {
        return slbid;
    }

    public void setSlbid(int slbid) {
        this.slbid = slbid;
    }
    

    public int getPreReqID() {
        return preReqID;
    }

    public void setPreReqID(int preReqID) {
        this.preReqID = preReqID;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getPreReqCode() {
        return preReqCode;
    }

    public void setPreReqCode(String preReqCode) {
        this.preReqCode = preReqCode;
    }

    @Override
    public String toString() {
        return "PreRequisite{" + "preReqID=" + preReqID + ", slbid=" + slbid + ", subjectCode=" + subjectCode + ", preReqCode=" + preReqCode + '}';
    }
    
    
}
