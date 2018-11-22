package com.example.findfood.bean;

public class User {

	private int U_id;
	
	private String U_account;//账号
	
	private String U_password;//密码
	
	private String U_name;//姓名
	
	private String U_sex;//性别
	
	private String U_phone;//电话
	
	private String U_address;//地址
	
	private float U_balance;//余额
	
	private String U_birthday;//生日

	private String U_headP;//头像
	public int getU_id() {
		return U_id;
	}

	public void setU_id(int u_id) {
		U_id = u_id;
	}

	public String getU_account() {
		return U_account;
	}

	public void setU_account(String u_account) {
		U_account = u_account;
	}

	public String getU_password() {
		return U_password;
	}

	public void setU_password(String u_password) {
		U_password = u_password;
	}

	public String getU_sex() {
		return U_sex;
	}

	public void setU_sex(String u_sex) {
		U_sex = u_sex;
	}

	public String getU_phone() {
		return U_phone;
	}

	public void setU_phone(String u_phone) {
		U_phone = u_phone;
	}

	public String getU_address() {
		return U_address;
	}

	public void setU_address(String u_address) {
		U_address = u_address;
	}

	public float getU_balance() {
		return U_balance;
	}

	public void setU_balance(float u_balance) {
		U_balance = u_balance;
	}

	public String getU_name() {
		return U_name;
	}

	public void setU_name(String u_name) {
		U_name = u_name;
	}

	public String getU_birthday() {
		return U_birthday;
	}

	public void setU_birthday(String U_birthday) {
		this.U_birthday = U_birthday;
	}

	public String getU_headP() {
		return U_headP;
	}

	public void setU_headP(String u_headP) {
		U_headP = u_headP;
	}
	
}
