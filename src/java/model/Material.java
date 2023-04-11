/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author phanh
 */
public class Material {
    private int mid;
    private int slbid;
    private String description;
    private String author;
    private String publisher;
    private Date publishedDate;
    private String edition;
    private String ISBN;
    private boolean isMainMaterial;
    private boolean isHardCopy;
    private boolean isOnline;
    private boolean isActive;
    private String note;
    private int accountID;

    public Material() {
    }

    public Material(int mid, int slbid, String description, String author, String publisher, Date publishedDate, String edition, String ISBN, boolean isMainMaterial, boolean isHardCopy, boolean isOnline, boolean isActive, String note, int accountID) {
        this.mid = mid;
        this.slbid = slbid;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.edition = edition;
        this.ISBN = ISBN;
        this.isMainMaterial = isMainMaterial;
        this.isHardCopy = isHardCopy;
        this.isOnline = isOnline;
        this.isActive = isActive;
        this.note = note;
        this.accountID = accountID;
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

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getSlbid() {
        return slbid;
    }

    public void setSlbid(int slbid) {
        this.slbid = slbid;
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isIsMainMaterial() {
        return isMainMaterial;
    }

    public void setIsMainMaterial(boolean isMainMaterial) {
        this.isMainMaterial = isMainMaterial;
    }

    public boolean isIsHardCopy() {
        return isHardCopy;
    }

    public void setIsHardCopy(boolean isHardCopy) {
        this.isHardCopy = isHardCopy;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
