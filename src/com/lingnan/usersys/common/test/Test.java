package com.lingnan.usersys.common.test;

import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.controller.UserController;
import com.lingnan.usersys.usermgr.domain.UserVO;
import com.lingnan.usersys.usermgr.view.IndexFrame;

public class Test {

	public static void main(String[] args) {
//		IndexFrame idfr = new IndexFrame();
//		idfr.show();
		UserVO user = new UserVO();
		user.setUserID(8);
		user.setUserName("888");
		user.setPasswd("888");
		user.setMailbox("2018@123mandy.com");
		user.setPower("普通用户");
		user.setBirth(TypeUtils.strToDate("2018-09-19"));
		UserController uc = new UserController();
		System.out.println(uc.doAddUser(user));

	}

}
