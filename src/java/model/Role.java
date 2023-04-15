/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phanh
 */
public class Role {
    private int rid;
    private String rname;
    private int status;
    private int displayOrder;
    private String description;
    private String type;

    public Role(int rid, String rname, int status, int displayOrder, String description, String type) {
        this.rid = rid;
        this.rname = rname;
        this.status = status;
        this.displayOrder = displayOrder;
        this.description = description;
        this.type = type;
    }

    
    public Role() {
    }

    public Role(int rid, String rname) {
        this.rid = rid;
        this.rname = rname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
    
    
}
