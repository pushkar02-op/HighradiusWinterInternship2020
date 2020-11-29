package com.highradius.internship;


import java.io.Serializable;

public class Response implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String level;
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public void setUserLevel(String level) {
    	this.level = level;
    }
    public String getUserLevel() {
    	return level;
    }
}
