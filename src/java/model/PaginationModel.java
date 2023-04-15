/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class PaginationModel {
    private int pageNo;
    private int pageSize;
    private String search;
    private int filterRole;
    private int filterStatus;
    
    private String filterType;

    public PaginationModel() {
    }

    public PaginationModel(int pageNo, int pageSize, String search, int filterStatus, String filterType, boolean s) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.search = search;
        this.filterStatus = filterStatus;
        this.filterType = filterType;
    }
    
    
    public PaginationModel(int pageNo, int pageSize, String search, int filterRole, int filterStatus) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.search = search;
        this.filterRole = filterRole;
        this.filterStatus = filterStatus;
    }

    public PaginationModel(int pageNo, int pageSize, String search) {
                this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.search = search;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }
    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getFilterRole() {
        return filterRole;
    }

    public void setFilterRole(int filterRole) {
        this.filterRole = filterRole;
    }

    public int getFilterStatus() {
        return filterStatus;
    }

    public void setFilterStatus(int filterStatus) {
        this.filterStatus = filterStatus;
    }
    
    
    
}
