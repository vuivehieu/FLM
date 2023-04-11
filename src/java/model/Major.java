
package model;

public class Major {
    private int majorID;
    private String majorName_EN;
    private String majorName_VI;
    private String keyword;
    private String image;

    public Major() {
    }

    public Major(int majorID, String majorName_EN, String majorName_VI, String keyword, String image) {
        this.majorID = majorID;
        this.majorName_EN = majorName_EN;
        this.majorName_VI = majorName_VI;
        this.keyword = keyword;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String images) {
        this.image = images;
    }

    

    public int getMajorID() {
        return majorID;
    }

    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    public String getMajorName_EN() {
        return majorName_EN;
    }

    public void setMajorName_EN(String majorName_EN) {
        this.majorName_EN = majorName_EN;
    }

    public String getMajorName_VI() {
        return majorName_VI;
    }

    public void setMajorName_VI(String majorName_VI) {
        this.majorName_VI = majorName_VI;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    
}
