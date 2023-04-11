package model;

import java.sql.Date;

public class Account {

    private int accountID;
    private String userName;
    private String password;
    private String displayName;
    private String email;
    private String avatar;
    private boolean isBlock;
    private int status;
    private Date createDate;
    private Role role;

    public Account() {
    }

    public Account(int accountID, String userName, String password, String displayName, String email, String avatar, boolean isBlock, int status, Date createDate, Role role) {
        this.accountID = accountID;
        this.userName = userName;
        this.password = password;
        this.displayName = displayName;
        this.email = email;
        this.avatar = avatar;
        this.isBlock = isBlock;
        this.status = status;
        this.createDate = createDate;
        this.role = role;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isIsBlock() {
        return isBlock;
    }

    public void setIsBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
