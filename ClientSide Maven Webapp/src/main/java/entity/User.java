package entity;

import java.io.Serializable;

public class User implements Serializable{
	
	public User() {
		super();
	}
	private String upwd;// 用户id
	private String userName;// 用户名

	public User( String userName,String  upwd){
		this.upwd = upwd;
		this.userName = userName;
	}

	public String getupwd() {
		return upwd;
	}

	public void setupwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
