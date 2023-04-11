package model;
import java.util.List;


public class Assessment {

    private int assid;
    private int slbid;
    private String category;
    private String type;
    private int part;
    private float weight;
    private String completionCriteria;
    private String duration;
    private String questionType;
    private String noQuestion;
    private String knowledgeSkill;
    private String gradingGuide;
    private String note;
    private boolean isActive;
    private List<AssessmentDetail> detail;
    private int accountID;

    public Assessment() {
    }

    public Assessment(int assid, int slbid, String category, String type, int part, float weight, String completionCriteria, String duration, String questionType, String noQuestion, String knowledgeSkill, String gradingGuide, String note, boolean isActive, List<AssessmentDetail> detail, int accountID) {
        this.assid = assid;
        this.slbid = slbid;
        this.category = category;
        this.type = type;
        this.part = part;
        this.weight = weight;
        this.completionCriteria = completionCriteria;
        this.duration = duration;
        this.questionType = questionType;
        this.noQuestion = noQuestion;
        this.knowledgeSkill = knowledgeSkill;
        this.gradingGuide = gradingGuide;
        this.note = note;
        this.isActive = isActive;
        this.detail = detail;
        this.accountID = accountID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    

    public int getAssid() {
        return assid;
    }

    public void setAssid(int assid) {
        this.assid = assid;
    }

    public int getSlbid() {
        return slbid;
    }

    public void setSlbid(int slbid) {
        this.slbid = slbid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCompletionCriteria() {
        return completionCriteria;
    }

    public void setCompletionCriteria(String completionCriteria) {
        this.completionCriteria = completionCriteria;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getNoQuestion() {
        return noQuestion;
    }

    public void setNoQuestion(String noQuestion) {
        this.noQuestion = noQuestion;
    }

    public String getKnowledgeSkill() {
        return knowledgeSkill;
    }

    public void setKnowledgeSkill(String knowledgeSkill) {
        this.knowledgeSkill = knowledgeSkill;
    }

    public String getGradingGuide() {
        return gradingGuide;
    }

    public void setGradingGuide(String gradingGuide) {
        this.gradingGuide = gradingGuide;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<AssessmentDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<AssessmentDetail> detail) {
        this.detail = detail;
    }

    

    

    public String getTotal() {
        float total = 0;
        for (AssessmentDetail ass : detail) {
            total += ass.getWeight();
        }
        
        return (int)(total * 100) + " %";
    }

}
