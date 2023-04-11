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
public class Lecturer extends Account{
    private int lid;
    private String lname;

    public Lecturer() {
    }

    public Lecturer(int accountID, String userName, String password, String displayName, String email, String avatar, boolean isBlock, int status, Date createDate, Role role, int lid, String lname) {
        super(accountID, userName, password, displayName, email, avatar, isBlock, status, createDate, role);
        this.lid = lid;
        this.lname = lname;
    }

    
    
    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    
    
}
