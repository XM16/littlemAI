package com.hust.xiaomo16.entity;

import com.hust.xiaomo16.utils.KeyUtil;
import com.sun.org.apache.xml.internal.security.keys.KeyUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String UserID;

    private String username;

    private String password;

    public String getUserID() {
        return UserID;
    }

    public void setUserID() {
        UserID = KeyUtil.genUniqueKey();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
