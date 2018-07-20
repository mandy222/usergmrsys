package com.lingnan.usersys.usermgr.domain;

import java.util.Date;

/**
 * 用户信息类
 * @author 曾洁义
 *
 */
public class UserVO {	
	private int userID;//用户编号
	private String userName;//用户姓名
	private String passwd;//用户密码
	private String mailbox;//用户邮箱
	private String power;//用户权限
	private Date birth;//用户生日
	private int status;//信息状态
	
	/**
	 * 获取用户编号
	 * @return 用户编号
	 */
	public int getUserID() {
		return userID;
	}
	
	/**
	 * 设置用户编号为指定参数的值
	 * @param userID 用户编号
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	/**
	 * 获取用户姓名
	 * @return 用户姓名
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 设置用户姓名为指定参数的值
	 * @param userName 用户姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取用户密码
	 * @return 用户密码
	 */
	public String getPasswd() {
		return passwd;
	}
	
	/**
	 * 设置用户密码为指定参数的值
	 * @param passwd
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	/**
	 * 获取用户邮箱
	 * @return 用户邮箱
	 */
	public String getMailbox() {
		return mailbox;
	}
	
	/**
	 * 设置用户邮箱为指定参数的值
	 * @param mailbox 用户邮箱
	 */
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	
	/**
	 * 获取用户权限
	 * @return 用户权限
	 */
	public String getPower() {
		return power;
	}
	
	/**
	 * 设置用户权限为指定参数的值
	 * @param power 用户权限
	 */
	public void setPower(String power) {
		this.power = power;
	}
	
	/**
	 * 获取用户生日
	 * @return 用户生日
	 */
	public Date getBirth() {
		return birth;
	}
	
	/**
	 * 设置用户生日为指定参数的值
	 * @param birth 用户生日
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	/**
	 * 获取用户信息状态
	 * @return 用户信息状态
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置用户信息状态为指定参数的值
	 * @param status 用户信息状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
}
