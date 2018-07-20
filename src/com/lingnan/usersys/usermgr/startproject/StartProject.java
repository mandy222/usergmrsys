package com.lingnan.usersys.usermgr.startproject;

import com.lingnan.usersys.usermgr.view.IndexFrame;

/**
 * 系统启动类
 * @author 曾洁义
 *
 */
public class StartProject {
	/**
	 * 系统启动方法
	 * @param args
	 */
	public static void main(String[] args) {
		IndexFrame frame = new IndexFrame();//实例化用户登陆和注册操作页面
		frame.show();//调用loginFrame方法，显示用户登陆和注册页面
	}

}
