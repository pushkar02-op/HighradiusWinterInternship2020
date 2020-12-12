package com.highradius.internship;


import java.io.Serializable;

public class Response implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
//    private String level;
    private int order_id;
    private String cust_name;
    private int cust_id;
    private int order_amt;
    private String appr_status;
    private String approved_by;
    private String notes;
    private String order_date;
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
//	public String getLevel() {
//		return level;
//	}
//	public void setLevel(String level) {
//		this.level = level;
//	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getOrder_amt() {
		return order_amt;
	}
	public void setOrder_amt(int order_amt) {
		this.order_amt = order_amt;
	}
	public String getAppr_status() {
		return appr_status;
	}
	public void setAppr_status(String appr_status) {
		this.appr_status = appr_status;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
    
    
    
}
