package com.lingnan.usersys.usermgr.view;

import java.util.Date;
import java.util.Scanner;

import com.lingnan.usersys.common.util.TypeUtils;
import com.lingnan.usersys.usermgr.controller.UserController;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 普通用户视图类
 * 用于用户管理：权限分为普通用户和管理员
 * @author 曾洁义
 *
 */
public class NormalFrame extends IndexFrame {

	UserVO user = null;//声明用户对象为成员	
	
	/**
	 * 带参数的构造器，用于初始化user属性
	 * @param user
	 */
	public NormalFrame(UserVO user){
		this.user = user;
	}
	
	/**
	 * 普通用户登陆后的主窗体界面
	 */
	public void show(){
		Scanner reader = new Scanner(System.in);		
		while(true){
			System.out.println("\n+------------ #普通用户登陆主窗体# -----------+");
			System.out.println("|              1.修改个人信息                                        |");
			System.out.println("|              2.查询个人信息                                        |");
			System.out.println("|              3.注销个人信息                                        |");
			System.out.println("|              4.退出登陆                                                |");
			System.out.println("+----------------------------------------+");
			System.out.print(">>>请选择:");
			int choose = -1;
			while(true){
				try{
					choose = Integer.parseInt(reader.next());
					break;//中断该循环，进入下一步操作：choose值判断
				} catch(Exception e){
					//出现异常时，提示错误信息，需重新输入
					System.out.println("输入错误，只能输入1~4数字,请重新输入！");
				}
			}
			
			/**
			 * 判断用户输入值并进行相应的操作
			 */
			switch(choose){
			case 1://修改个人信息
				new IndexFrame().updateShow("普通用户", user);
				break;
			case 2://查询
				this.findShow();
				break;
			case 3://注销
				this.deleteShow();
				break;
			case 4:
				System.out.println("退出登陆成功......");
				new IndexFrame().show();
				break;
			default:
				System.out.println("输入错误，只能输入1~4数字,请重新输入！");
			}
		}
	}

	/**
	 * 普通用户注销个人信息界面
	 */
	public void deleteShow(){
		Scanner reader = new Scanner(System.in);
		UserController uc = new UserController();
		try{					
			if(uc.doDeleteUser(user.getUserID())){
				System.out.println("个人信息注销成功......");
				new IndexFrame().show();
			}
			else{
				System.out.println("个人信息注销失败......");
				this.show();
			}		
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}	
	}
	
	/**
	 * 普通用户查询个人信息界面
	 */
	public void findShow(){
		Scanner reader = new Scanner(System.in);
		
		try{	
			System.out.println("\n------------------------ *个人信息* ------------------------------------");
			System.out.println(user.getUserID()+"\t"+user.getUserName()+"\t"+user.getPasswd()+"\t"+user.getMailbox()+"\t"+user.getPower()+"\t"+TypeUtils.dateToString(user.getBirth()));		
			System.out.println("----------------------------------------------------------------------");	
			this.show();
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}	
	}
	


}
