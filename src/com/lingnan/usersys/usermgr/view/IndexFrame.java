package com.lingnan.usersys.usermgr.view;

import java.util.Date;
import java.util.Scanner;

import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.controller.UserController;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用户视图接口实现类
 * 用于用户登陆和注册操作
 * @author 曾洁义
 *
 */
public class IndexFrame implements BaseFrame {
	
	/**
	 * 用户登陆和注册页面
	 */
	public void show() {
		Scanner reader = new Scanner(System.in);		
		while(true){
			System.out.println("\n+-----------------------------------+");
			System.out.println("|       #欢迎使用阿姨的用户管理系统#       |");
			System.out.println("+-----------------------------------+");
			System.out.println("|          1.用户登陆                                             |");
			System.out.println("|          2.用户注册                                             |");
			System.out.println("|          3.退出                                                     |");
			System.out.println("+-----------------------------------+");
			System.out.print(">>>请选择:");
			int choose = -1;
			while(true){
				try{
					choose = Integer.parseInt(reader.next());
					break;//中断该循环，进入下一步操作：choose值判断
				} catch(Exception e){
					//出现异常时，提示错误信息，需重新输入
					System.out.println("输入错误，只能输入1~3数字,请重新输入！");
				}
			}			
			/**
			 * 判断用户输入值并进行相应的操作
			 */
			switch(choose){
			case 1://登陆
				this.loginShow();
				break;
			case 2://注册
				this.addShow("注册");
				break;
			case 3://退出
				System.out.println("\n退出成功，欢迎再次使用本系统......");
				System.exit(0);
				break;
			default:
				System.out.println("输入错误，只能输入1~3数字,请重新输入！");
			}
		}
	}

	/**
	 * 用户登陆界面
	 */
	public void loginShow() {
		Scanner reader = new Scanner(System.in);
		System.out.println("\n--------- *欢迎进入用户登陆界面* ------------");
		System.out.print(">>请输入用户名：");
		try{
			String userName = reader.next();
			System.out.print(">>请输入您的用户密码：");
			String passwd = reader.next();
			UserController uc = new UserController();
			UserVO user = uc.doLogin(userName, passwd);
			if(user != null){
				System.out.println("登陆成功......");
				//调用主页面
				AdminFrame m = new AdminFrame(user);
				m.loginSuccShow();//去到 AdminFrame 再判断是普通用户还是管理员			
				System.exit(0);//退出当前页面		
				}
			else{
				System.out.println("登陆失败......");
			}
		System.out.println("----------------------------------------");
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}
	}
	
	/**
	 * 用户添加界面
	 */
	public void addShow(String type) {
		Scanner reader = new Scanner(System.in);
		UserVO user =new UserVO();
		if(type.equals("添加用户")){
			System.out.println("\n------------ *管理员添加用户* ------------");
		}
		else{
			System.out.println("\n------------- *用户注册界面* ------------");				
		}		
		try{
			System.out.print(">>请输入用户名：");
			user.setUserName(reader.next());
			System.out.print(">>请输入您的用户密码：");
			user.setPasswd(reader.next());
			System.out.print(">>请输入用户邮箱：");
			while(true){
				String mailbox = reader.next();
				if(TypeUtils.checkEmail(mailbox))//检查邮箱格式是否正确
				{
					user.setMailbox(mailbox);
					break;
				}
			}			
			System.out.print(">>请输入用户生日(YYYY-MM-DD):");	
			while(true) {
				try{									
					Date birth = TypeUtils.strToDate(reader.next());
					user.setBirth(birth);
					break;
				} catch(Exception e){
					System.out.println("输入生日格式出现错误！");
					System.out.print(">>请重新输入用户生日(YYYY-MM-DD):");	
				}
			}		
			UserController uc = new UserController();			
			if(type.equals("添加用户")){//管理员添加用户
				System.out.print(">>请输入要修改的用户权限(管理员/普通用户)：");
				user.setPower(reader.next());
				if(uc.doAddUser(user)){
					System.out.println("添加用户成功......");
				}
				else {
					System.out.println("添加用户失败......");
				}
				System.out.println("--------------------------------------");	
			}
			else{//普通用户进行注册
				user.setPower("普通用户");
				if(uc.doAddUser(user)){
					System.out.println("注册成功......");
				}
				else{
					System.out.println("注册失败......");
				}		
				System.out.println("--------------------------------------");	
				new IndexFrame().show();//回到登陆注册界面
			}				
			
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}				
	}

	/**
	 * 用户查询操作界面
	 */
	public void searchShow() {
		Scanner reader = new Scanner(System.in);		
		while(true){

			System.out.println("\n+------------------- #管理员查询界面# -----------------+");
			System.out.println("|  1.查询全部用户信息(当记录大于10条时分页显示查询结果，每页10条) |");
			System.out.println("|  2.分页查询                                                                                                          |");
			System.out.println("|  3.根据信息状态查询用户信息(1为有效，0为无效，2为全部信息)     |");                                                                                                        
			System.out.println("|  4.根据UserID查询用户信息                                                                           |");
			System.out.println("|  5.根据UserName查询用户信息                                                                      |");
			System.out.println("|  6.退出查询                                                                                                          |");
			System.out.println("+---------------------------------------------------+");
			System.out.print(">>>请选择:");
			int choose = -1;
			while(true){
				try{
					choose = Integer.parseInt(reader.next());
					break;//中断该循环，进入下一步操作：choose值判断
				} catch(Exception e){
					//出现异常时，提示错误信息，需重新输入
					System.out.println("输入错误，只能输入1~4数字,请重新输入!");
				}
			}			
			/**
			 * 判断用户输入值并进行相应的操作
			 */
			switch(choose){
			case 1://查询全部用户信息(当记录大于10条时分页显示查询结果，每页10条)
				new AdminFrame(null).finAllUserPageShow();
				break;
			case 2://分页查询
				new AdminFrame(null).finUserPageShow();
				break;
			case 3://根据信息状态查询用户信息(1为有效，0为无效，2为全部信息)
				new AdminFrame(null).finAllUserShow();
				break;
			case 4://根据UserID查询用户信息
				new AdminFrame(null).finUserByUserIDShow();
				break;
			case 5://根据UserName查询用户信息
				new AdminFrame(null).finUserByUserNameShow();
				break;
			case 6://退出查询
				new AdminFrame(null).show();;
				break;
			default:
				System.out.println("输入错误，只能输入1~4数字,请重新输入!");
			}
		}		
	}

	/**
	 * 用户更新操作界面
	 */
	public void updateShow(String type, UserVO user) {
		Scanner reader = new Scanner(System.in);
		try{
			if(type.equals("管理员")){
				System.out.println("\n---------- *管理员修改用户信息* -----------");
				System.out.print(">>请输入要修改的用户的用户编号：");
				int userID = Integer.parseInt(reader.next());			
				if(new UserController().doFindUserByUserID(userID) !=null ){//查询表中是否有该用户
					user.setUserID(userID);
				}
				else{
					System.out.println("无该用户信息......");
					new AdminFrame(user).show();//回到管理员主窗体
				}
			}
			else{	
				System.out.println("\n-------------------------- *当前个人信息* ------------------------------");
				System.out.println(user.getUserID()+"\t"+user.getUserName()+"\t"+user.getPasswd()+"\t"+user.getMailbox()+"\t"+user.getPower()+"\t"+user.getBirth()+"\t"+user.getStatus());		
				System.out.println("---------------------------------------------------------------------");
				System.out.println("---------------------------- *修改个人信息* ----------------------------");
			}		
		
			System.out.print(">>请输入要修改的用户名：");
			user.setUserName(reader.next());
			System.out.print(">>请输入要修改的的用户密码：");
			user.setPasswd(reader.next());
			System.out.print(">>请输入要修改的用户邮箱：");
			while(true){
				String mailbox = reader.next();
				if(TypeUtils.checkEmail(mailbox))//检查邮箱格式是否正确
				{
					user.setMailbox(mailbox);
					break;
				}
			}
			System.out.print(">>>>请输入要修改的用户生日(YYYY-MM-DD):");	
			while(true) {
				try{									
					Date birth = TypeUtils.strToDate(reader.next());
					user.setBirth(birth);
					break;
				} catch(Exception e){
					System.out.println("输入生日格式出现错误！");
					System.out.print(">>请重新要修改的用户生日(YYYY-MM-DD):");	
				}
			}				
			UserController uc = new UserController();			
			if(type.equals("管理员")){//管理员更改用户信息
				System.out.print(">>请输入要修改的用户权限(管理员/普通用户)：");
				user.setPower(reader.next());
				System.out.print(">>请输入要修改的用户信息状态：");
				user.setStatus(Integer.parseInt(reader.next()));;
				if(uc.doUpdateUser(user)){
					System.out.println("用户信息修改成功......");
				}
				else {
					System.out.println("用户信息修改失败......");
				}
				System.out.println("---------------------------------------------------------------------");
				new AdminFrame(user).show();//回到管理员主窗体
			}
			else{//普通用户进行个人信息修改
				if(uc.doUpdateUser(user)){
					System.out.println("个人信息修改成功......");
				}
				else{
					System.out.println("个人信息修改失败......");
				}			
				System.out.println("---------------------------------------------------------------------");
				new NormalFrame(user).show();//回到普通用户主窗体
			}				
			
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}				
		
	}
	
}
