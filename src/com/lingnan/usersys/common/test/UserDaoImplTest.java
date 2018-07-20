package com.lingnan.usersys.common.test;

import java.sql.Connection;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.lingnan.usersys.common.util.DBUtils;
import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.business.dao.UserDaoImpl;
import com.lingnan.usersys.usermgr.domain.UserVO;

public class UserDaoImplTest {

	@Before
	public void setUp() throws Exception {
	}

	//登陆
//	@Test
//	public void testLogin() {
//		Connection conn = DBUtils.getConnection();
//		UserDaoImpl u = new UserDaoImpl(conn);
//		String _userName = "111";
//		String _passwd = "111";		
////		System.out.println(u.login(_userName, _passwd));
//		UserVO user = new UserVO();
//		user = u.login(_userName, _passwd);
//		System.out.println("userName:"+user.getUserName()+" status:"+user.getStatus());		
//	}

	//注册/增加
//	@Test
//	public void testAddUser() {
//		Connection conn = DBUtils.getConnection();
//		UserDaoImpl u = new UserDaoImpl(conn);
//		UserVO user = new UserVO();
//		user.setUserName("10101");
//		user.setPasswd("999");
//		user.setMailbox("2018@123mandy.com");
//		user.setPower("普通用户");
//		user.setBirth(TypeUtils.strToDate("2018-09-19"));
//		u.addUser(user);
//		System.out.println("newAddUserID: "+user.getUserName()); 
//	}
	
	//删除
//	@Test
//	public void testDeleteUser() {
//		Connection conn = DBUtils.getConnection();
//		UserDaoImpl u = new UserDaoImpl(conn);
//		System.out.println(u.deleteUser(26));
//	}
	
	//查询所有用户信息
//	@Test
//	public void testFindAllUser() {
//		Connection conn = DBUtils.getConnection();
//		Vector<UserVO> v = new Vector<UserVO>();
//		UserDaoImpl u = new UserDaoImpl(conn);	
//		v = u.findAllUser(2);
//		System.out.println("t_user table:");
//		for(UserVO user : v)
//			System.out.println(user.getUserID()+"\t"+user.getUserName()+"\t"+user.getPasswd()+"\t"+user.getMailbox()+"\t"+user.getPower()+"\t"+user.getBirth()+"\t"+user.getStatus());
//	}

	//根据UserID查询用户信息
//	@Test
//	public void testFindUserByUserID() {
//		Connection conn = DBUtils.getConnection();
//		UserDaoImpl u = new UserDaoImpl(conn);
//		int _userID = 9;
//		UserVO user = new UserVO();
//		user = u.findUserByUserID(_userID);
//		System.out.println(user.getUserID()+"\t"+user.getUserName()+"\t"+user.getPasswd()+"\t"+user.getMailbox()+"\t"+user.getPower()+"\t"+user.getBirth()+"\t"+user.getStatus());		
//	}
	
	//根据userName查询用户信息
//	@Test
//	public void testFindUserByUserName() {
//		Connection conn = DBUtils.getConnection();
//		Vector<UserVO> v = new Vector<UserVO>();
//		UserDaoImpl u = new UserDaoImpl(conn);	
//		v = u.findUserByUserName("88");
//		for(UserVO user : v)
//			System.out.println(user.getUserID()+"\t"+user.getUserName()+"\t"+user.getPasswd()+"\t"+user.getMailbox()+"\t"+user.getPower()+"\t"+user.getBirth()+"\t"+user.getStatus());
//	}

	//修改用户信息
//	@Test
//	public void testUpdateUser() {
//		Connection conn = DBUtils.getConnection();
//		UserDaoImpl u = new UserDaoImpl(conn);
//		UserVO user = new UserVO();
//		user.setUserID(11);
//		user.setUserName("000");
//		user.setPasswd("000");
//		user.setMailbox("000@000mandy.com");
//		user.setPower("管理员");
//		user.setBirth(TypeUtils.strToDate("2000-09-10"));
//		user.setStatus(1);
//		u.updateUser(user);
//		System.out.println("newAddUserID: "+user.getUserName()); 
//	}
	
	//统计表的的记录总数	
//	@Test
//	public void testCountUserNum() {
//		Connection conn = DBUtils.getConnection();
//		UserDaoImpl u = new UserDaoImpl(conn);
//		System.out.println(u.countUserNum()); 
//	}
		
	//分页查询
//	@Test
//	public void testFindUserPage() {
//		Connection conn = DBUtils.getConnection();
//		Vector<UserVO> v = new Vector<UserVO>();
//		UserDaoImpl u = new UserDaoImpl(conn);	
//		v = u.findAllUserPage(1,20);
//		for(UserVO user : v)
//			System.out.println(user.getUserID()+"\t"+user.getUserName()+"\t"+user.getPasswd()+"\t"+user.getMailbox()+"\t"+user.getPower()+"\t"+user.getBirth()+"\t"+user.getStatus());
//	}
}
