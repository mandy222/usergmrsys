package com.lingnan.usersys.usermgr.view;

import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用户视图接口
 * @author mandy
 *
 */
public interface BaseFrame {
	/**
	 * 页面显示
	 */
	public void show();
	
	/**
	 * 用户增加操作页面显示
	 * @param type
	 */
	public void addShow(String type);
	
	/**
	 * 用户查询操作页面显示
	 */
	public void searchShow();
	
	/**
	 * 用户更新操作页面显示
	 * @param type
	 * @param user
	 */
	public void updateShow(String type,UserVO user);

	
}
