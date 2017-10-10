package com.dto;

import java.io.Serializable;

public class PasswordDTO implements Serializable{
    private static final long serialVersionUID = -7447591702866274446L;
    private long passwordID;
    private String password_;
    private long userIdFk;

    public long getPasswordID() {
        return passwordID;
    }

    public void setPasswordID(long passwordID) {
        this.passwordID = passwordID;
    }

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
    }

    public long getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(long userIdFk) {
        this.userIdFk = userIdFk;
    }
}
